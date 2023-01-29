package fa.training.service;

import java.util.List;

import fa.training.model.PageTraineeProfileDto;
import fa.training.model.TraineeProfileDto;

public interface TraineeProfileService {
	public TraineeProfileDto getTraineeProfileById(Long id);
	public PageTraineeProfileDto getTraineeProfileByClassId(Long classId, Integer offSet, Integer pageSize);
	public PageTraineeProfileDto getTraineeList(Integer offset, Integer pageSize);
	public TraineeProfileDto getTraineeProfileByAccount(String account);
	public String deleteTraineeProfileById(List<Long> traineeIds);  
}
