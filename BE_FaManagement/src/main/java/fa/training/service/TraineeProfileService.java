package fa.training.service;


import java.util.List;

import org.springframework.data.domain.Page;

import fa.training.model.CandidateForm;
import fa.training.model.TraineeProfileDto;

public interface TraineeProfileService {
	public boolean isDuplicateProfile(CandidateForm candidateForm);

	public TraineeProfileDto getTraineeProfileById(Long id);
	
	public Page<TraineeProfileDto> getPanigationTraineeByClassId(Long classId,Integer offset, Integer pageSize);

	public Page<TraineeProfileDto> getPaginationTrainee(Integer offset, Integer pageSize);

	public TraineeProfileDto getTraineeProfileByAccount(String account);

	public Page<TraineeProfileDto> getPaginationTraineeNotInClass(Long classId, Integer offset, Integer pageSize);

	public void deleteTraineeProfileById(List<Long> traineeIds);
}
