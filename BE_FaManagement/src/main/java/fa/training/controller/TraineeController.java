package fa.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fa.training.model.ResponseObject;
import fa.training.model.TraineeUpdateStatusDto;
import fa.training.service.TraineeService;
import fa.training.util.MESSAGE;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/trainee")
@CrossOrigin
@Slf4j
public class TraineeController {

	@Autowired
	private TraineeService traineeService;
	
	@PutMapping("/update-status")
	public ResponseEntity<?> updateStatusInClass(@RequestBody List<TraineeUpdateStatusDto> traineeUpdateStatusDtos)
	{
		ResponseEntity<Object> responseEntity = ResponseEntity.badRequest().body("Update trainee status in class failed");
		try {
			traineeService.updateStatusInClass(traineeUpdateStatusDtos);
			responseEntity = ResponseEntity.ok().body(
					new ResponseObject(String.valueOf(HttpStatus.OK), MESSAGE.MSG22.msg , null));
		} catch (Exception e) {
			log.error("Update trainee status in class failed ");
		}
		return responseEntity;
	}
}
