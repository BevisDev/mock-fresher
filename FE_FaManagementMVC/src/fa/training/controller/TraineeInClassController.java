package fa.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fa.training.model.TraineeUpdateStatusDto;
import fa.training.service.TraineeInClassService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/trainee-in-class")
@Slf4j
public class TraineeInClassController {

	@Autowired
	private TraineeInClassService traineeService;
	
	@PostMapping("/update-status")
	@ResponseBody
	public String updateTraineeStatusInClass(@RequestBody List<TraineeUpdateStatusDto> traineeUpdateStatusDtos)
	{
		String message = "Update status for trainee failed";
		try {
			message = traineeService.updateStatusInClass(traineeUpdateStatusDtos);
		}catch (Exception e) {
			log.error(message);
		}
		return message;
	}
	
}
