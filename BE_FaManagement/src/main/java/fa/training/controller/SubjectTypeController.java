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

import fa.training.model.SubjectTypeDto;
import fa.training.model.ResponseObject;
import fa.training.service.SubjectTypeService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/subject-type")
@CrossOrigin
@Slf4j
public class SubjectTypeController {

	@Autowired
	private SubjectTypeService subjectTypeService;

	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_DELIVERYMANAGER','ROLE_SYSTEM','ROLE_CLASSADMIN')")
	public ResponseEntity<?> getAllSubjectType() {
		ResponseEntity<Object> responseEntity = ResponseEntity.badRequest().body("Get all subject type failed");
		try {
			List<SubjectTypeDto> subjectTypeDtos = subjectTypeService.getAllSubjectType();
			responseEntity = ResponseEntity.ok().body(
					new ResponseObject(String.valueOf(HttpStatus.OK), "Get all subject type success", subjectTypeDtos));
		} catch (Exception e) {
			log.error("Get all subject type failed ");
		}
		return responseEntity;
	}
}
