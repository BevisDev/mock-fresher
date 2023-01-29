package fa.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fa.training.model.ResponseObject;
import fa.training.model.TraineeProfileDto;
import fa.training.service.TraineeProfileService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/trainee-profile")
@CrossOrigin
@Slf4j
public class TraneeProfileController {
	@Autowired
	private TraineeProfileService traineeProfileService;

	@GetMapping("/get-all")
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_DELIVERYMANAGER','ROLE_SYSTEM', 'ROLE_REC', 'ROLE_CLASSADMIN')")
	public ResponseEntity<?> traineeList(@RequestParam(defaultValue = "0") Integer offset,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		try {
			Page<TraineeProfileDto> traineeProfileDtos = traineeProfileService.getPaginationTrainee(offset, pageSize);
			responseEntity = ResponseEntity.ok().body(traineeProfileDtos);
		} catch (Exception e) {
			log.error("Get all trainee failed");
		}
		return responseEntity;
	}

	@GetMapping("/get-trainee-profile")
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_DELIVERYMANAGER','ROLE_SYSTEM', 'ROLE_REC', 'ROLE_CLASSADMIN')")
	public ResponseEntity<?> getTraineeProfileById(@RequestParam("id") Long id) {
		ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		try {
			TraineeProfileDto traineeProfileDto = traineeProfileService.getTraineeProfileById(id);
			if (traineeProfileDto != null) {
				responseEntity = ResponseEntity.ok().body(traineeProfileDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Get trainee profile by id is failed ");
		}
		return responseEntity;
	}

	@GetMapping("/get-trainee-infor")
	public ResponseEntity<?> getTraineeProfileByAccount(@RequestParam("account") String account) {
		ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		try {
			TraineeProfileDto traineeProfileDto = traineeProfileService.getTraineeProfileByAccount(account);
			responseEntity = ResponseEntity.ok().body(traineeProfileDto);
		} catch (Exception e) {
			log.error("Get trainee profile by account failed");
		}

		return responseEntity;
	}
	
	@PostMapping("/delete-trainee")
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_DELIVERYMANAGER','ROLE_SYSTEM')")
	public ResponseEntity<?> deleteTraineeProfileById(@RequestBody List<Long> traineeIds){
		ResponseEntity<Object> responseEntity = ResponseEntity.badRequest().body("Delete trainee profile by id failed");
		try {
			traineeProfileService.deleteTraineeProfileById(traineeIds);
			responseEntity = ResponseEntity.ok().body(new ResponseObject(String.valueOf(HttpStatus.OK),"Delete trainee by id success",null));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Delete trainee profiles by id failed");
		}
		return responseEntity;
	}
}
