package fa.training.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fa.training.entity.TraineeProfile;
import fa.training.mapper.TraineeProfileMapper;
import fa.training.model.CandidateForm;
import fa.training.model.TraineeProfileDto;
import fa.training.repository.TraineeProfileRepository;
import fa.training.service.TraineeProfileService;
import fa.training.util.MemberLogin;
import fa.training.util.TRAINEE_TYPE;

@Service
@Transactional(rollbackFor = Exception.class)
public class TraineeProfileServiceImpl implements TraineeProfileService {
	@Autowired
	private TraineeProfileRepository traineeProfileRepository;

	@Autowired
	private MemberLogin memberLogin;

	@Override
	public boolean isDuplicateProfile(CandidateForm candidateForm) {
		if (!traineeProfileRepository.findByFullName(candidateForm.getFullName()).isEmpty()) {
			if (!traineeProfileRepository.findByDateOfBirth(candidateForm.getDateOfBirth()).isEmpty()) {
				if (!traineeProfileRepository.findByPhone(candidateForm.getPhone()).isEmpty()) {
					if (!traineeProfileRepository.findByEmail(candidateForm.getEmail()).isEmpty()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public TraineeProfileDto getTraineeProfileById(Long id) {
		TraineeProfile traineeProfile = traineeProfileRepository.findById(id).get();
		return traineeProfile != null ? TraineeProfileMapper.INSTANCE.entityToDto(traineeProfile) : null;
	}

	@Override
	public Page<TraineeProfileDto> getPanigationTraineeByClassId(Long classId, Integer offset, Integer pageSize) {
		Pageable pageable = PageRequest.of(offset, pageSize);
		Page<TraineeProfile> traineeProfiles = traineeProfileRepository.findAllByClassId(classId, pageable);
		Long totalElements = traineeProfiles.getTotalElements();
		List<TraineeProfileDto> traineeProfileDtos = TraineeProfileMapper.INSTANCE
				.listEntityToListDto(traineeProfiles.getContent());
		return new PageImpl<>(traineeProfileDtos, pageable, totalElements);
	}

	@Override
	public Page<TraineeProfileDto> getPaginationTrainee(Integer offset, Integer pageSize) {
		Pageable pageAble = PageRequest.of(offset, pageSize);
		Page<TraineeProfile> traineeProfiles = traineeProfileRepository.findByIsDelete(false, pageAble);
		Long totalElements = traineeProfiles.getTotalElements();
		List<TraineeProfileDto> traineeProfileDtos = TraineeProfileMapper.INSTANCE
				.listEntityToListDto(traineeProfiles.getContent());
		return new PageImpl<>(traineeProfileDtos, pageAble, totalElements);
	}

	@Override
	public TraineeProfileDto getTraineeProfileByAccount(String account) {
		TraineeProfile traineeProfile = traineeProfileRepository.findByAccount(account).get();
		return TraineeProfileMapper.INSTANCE.entityToDto(traineeProfile);
	}

	@Override
	public Page<TraineeProfileDto> getPaginationTraineeNotInClass(Long classId, Integer offset, Integer pageSize) {
		Pageable pageAble = PageRequest.of(offset, pageSize);
		List<Long> traineeProfileClasss = traineeProfileRepository.findByClassIdByType(classId, "TRAINEE");
		Page<TraineeProfile> traineeProfilePages;
		if (traineeProfileClasss.size() == 0)
			traineeProfilePages = traineeProfileRepository.findAll(pageAble);
		else {
			traineeProfilePages = traineeProfileRepository.findAllByNotIn(traineeProfileClasss, pageAble);
		}
		Long totalElements = traineeProfilePages.getTotalElements();
		List<TraineeProfileDto> traineeProfiles = TraineeProfileMapper.INSTANCE
				.listEntityToListDto(traineeProfilePages.getContent());
		return new PageImpl<>(traineeProfiles, pageAble, totalElements);
	}

	@Override
	public void deleteTraineeProfileById(List<Long> traineeIds) {
		List<TraineeProfile> traineeProfileSelect = new ArrayList<>();
		traineeProfileSelect = traineeProfileRepository.findAllById(traineeIds);
		for (TraineeProfile traineeProfile : traineeProfileSelect) {
			if (!traineeProfile.getType().equals(TRAINEE_TYPE.TRAINEE.type)) {
				throw new IllegalArgumentException("Wrong trainee type");
			}

			String history = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a"))
					+ "_Deleted by " + memberLogin.getUserLogin().getUsername();
			traineeProfile.setHistory(traineeProfile.getHistory() + ", " + history);
			traineeProfile.setIsDelete(true);
			traineeProfileRepository.save(traineeProfile);
		}
	}

}
