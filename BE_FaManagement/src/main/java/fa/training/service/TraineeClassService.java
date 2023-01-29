package fa.training.service;

import org.springframework.web.multipart.MultipartFile;

import fa.training.model.TraineeClassDto;

public interface TraineeClassService {

	void addTraineeToClass(TraineeClassDto traineeClassDto);

	boolean importTraineeToClass(Long classId,MultipartFile file);
}
