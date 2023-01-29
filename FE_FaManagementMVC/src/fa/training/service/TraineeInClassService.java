package fa.training.service;

import java.util.List;

import fa.training.model.TraineeUpdateStatusDto;

public interface TraineeInClassService {

	public String updateStatusInClass(List<TraineeUpdateStatusDto> traineeUpdateStatusDtos);
}
