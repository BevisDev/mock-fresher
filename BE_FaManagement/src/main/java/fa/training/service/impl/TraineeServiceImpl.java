package fa.training.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fa.training.entity.Status;
import fa.training.entity.Trainee;
import fa.training.entity.TraineeProfile;
import fa.training.model.TraineeUpdateStatusDto;
import fa.training.repository.StatusInClassRepository;
import fa.training.repository.TraineeProfileRepository;
import fa.training.repository.TraineeRepository;
import fa.training.service.TraineeService;
import fa.training.util.MemberLogin;
import fa.training.util.TRAINEE_ALLOCATION_STATUS;
import fa.training.util.TRAINEE_STATUS;

@Service
@Transactional(rollbackFor = Exception.class)
public class TraineeServiceImpl implements TraineeService {

	@Autowired
	private TraineeProfileRepository traineeProfileRepository;;
	@Autowired
	private TraineeRepository traineeRepository;
	@Autowired
	private StatusInClassRepository statusRepository;
	@Autowired
	private MemberLogin memberLogin;

	
	@Override
	public void updateStatusInClass(List<TraineeUpdateStatusDto> traineeUpdateStatusDtos) {
		List<Trainee> trainees = new ArrayList<>();
		for (TraineeUpdateStatusDto dto : traineeUpdateStatusDtos) {
			//validate trainee profile status
			TraineeProfile traineeProfile = traineeProfileRepository.findById(dto.getTraineeProifileId()).get();
			if(traineeProfile.getStatus().equals(TRAINEE_STATUS.WAITING_FOR_CLASS.status) 
					|| traineeProfile.getStatus().equals(TRAINEE_STATUS.ENROLLED.status)
					|| traineeProfile.getStatus().equals(TRAINEE_STATUS.ALLOCATED.status))
			{
				throw new IllegalArgumentException("Wrong trainee profile status");
			}
			if(traineeProfile.getAllocationStatus().equals(TRAINEE_ALLOCATION_STATUS.ALLOCATED.status))
				throw new IllegalArgumentException("Wrong trainee alloctaion status");

			//update data for trainee in class
			Trainee trainee = traineeRepository.findByClassIdAndTraineeCandidateProfileId(dto.getClassId(),
					dto.getTraineeProifileId());
			Status status = statusRepository.findByStatusName(dto.getStatusName());
			trainee.setStatusId(status.getStatusId());
			trainee.setHistory(trainee.getHistory() + ", Updated by " + memberLogin.getUserLogin().getUsername());
			trainees.add(trainee);
		}
		traineeRepository.saveAll(trainees);
	}

}
