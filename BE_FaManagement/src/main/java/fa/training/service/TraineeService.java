package fa.training.service;

import java.util.List;

import fa.training.model.TraineeUpdateStatusDto;

public interface TraineeService {

	public void updateStatusInClass(List<TraineeUpdateStatusDto> traineeUpdateStatusDtos);
}
