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
import fa.training.model.StatusInClassDto;
import fa.training.service.StatusInClasService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/status-in-class")
@Slf4j
@CrossOrigin
public class StatusInClassController {
	@Autowired
	private StatusInClasService statusInClassService;

	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_CLASSADMIN','ROLE_MANAGER','ROLE_REC','ROLE_DELIVERYMANAGER','ROLE_TRAINER','ROLE_SYSTEM')")
	public ResponseEntity<?> getAllStatusInClass() {
		ResponseEntity<Object> responseEntity = ResponseEntity.badRequest().body("Get all status in class failed");
		try {
			List<StatusInClassDto> StatusInClassDtos = statusInClassService.getAllStatus();
			responseEntity = ResponseEntity.ok()
					.body(new ResponseObject(String.valueOf(HttpStatus.OK), "Get all status in class success", StatusInClassDtos));
		} catch (Exception e) {
			log.error("Get all status in class failed ");
		}
		return responseEntity;
	}
}
