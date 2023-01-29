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
import fa.training.model.SubSubjectTypeDto;
import fa.training.service.SubSubjectTypeService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/sub-subject-type")
@CrossOrigin
@Slf4j
public class SubSubjectTypeController {

	@Autowired
	private SubSubjectTypeService subSubjectTypeService;

	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_DELIVERYMANAGER','ROLE_SYSTEM','ROLE_CLASSADMIN')")
	public ResponseEntity<?> getAllSubjectType() {
		ResponseEntity<Object> responseEntity = ResponseEntity.badRequest().body("Get all sub-subject type failed");
		try {
			List<SubSubjectTypeDto> subSubjectTypeDtos = subSubjectTypeService.getAllSubSubjectType();
			responseEntity = ResponseEntity.ok().body(
					new ResponseObject(String.valueOf(HttpStatus.OK), "Get all sub-subject type success", subSubjectTypeDtos));
		} catch (Exception e) {
			log.error("Get all sub-subject type failed ");
		}
		return responseEntity;
	}
}
