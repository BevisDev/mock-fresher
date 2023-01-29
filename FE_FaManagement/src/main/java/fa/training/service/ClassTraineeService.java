package fa.training.service;

import org.springframework.web.multipart.MultipartFile;

import fa.training.model.PageTraineeProfileDto;
import fa.training.model.TraineeClassDto;

public interface ClassTraineeService {

	PageTraineeProfileDto getTraineeNotInClass(Long classId, Integer offset, Integer pageSize);

	String addTraineeToClass(TraineeClassDto traineeClassDto);

	String importTraineeToClass(MultipartFile file, Long classID);
}
