package fa.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fa.training.model.ResponseObject;
import fa.training.model.TrainerDto;
import fa.training.service.TrainerService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/trainer")
@CrossOrigin
@Slf4j
public class TrainerController {
	@Autowired
	private TrainerService trainerService;

	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_DELIVERYMANAGER','ROLE_SYSTEM','ROLE_CLASSADMIN')")
	public ResponseEntity<?> getAllSubjectType() {
		ResponseEntity<Object> responseEntity = ResponseEntity.badRequest().body("Get all trainer type failed");
		try {
			List<TrainerDto> trainerDtos = trainerService.getAllTrainer();
			responseEntity = ResponseEntity.ok().body(
					new ResponseObject(String.valueOf(HttpStatus.OK), "Get all trainer type success", trainerDtos));
		} catch (Exception e) {
			log.error("Get all trainer type failed ");
		}
		return responseEntity;
	}
}
