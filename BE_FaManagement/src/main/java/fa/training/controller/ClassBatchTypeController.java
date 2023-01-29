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

import fa.training.model.ClassBatchTypeDto;
import fa.training.model.ResponseObject;
import fa.training.service.ClassBatchTypeService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/class-batch-type")
@Slf4j
@CrossOrigin
public class ClassBatchTypeController {
	@Autowired
	private ClassBatchTypeService classBatchTypeService;

	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_DELIVERYMANAGER','ROLE_SYSTEM','ROLE_CLASSADMIN')")
	public ResponseEntity<?> getAllSubjectType() {
		ResponseEntity<Object> responseEntity = ResponseEntity.badRequest().body("Get all class batch type failed");
		try {
			List<ClassBatchTypeDto> classBatchTypeDtos = classBatchTypeService.getAllClassBatchType();
			responseEntity = ResponseEntity.ok().body(new ResponseObject(String.valueOf(HttpStatus.OK),
					"Get all class batch type success", classBatchTypeDtos));
		} catch (Exception e) {
			log.error("Get all class batch type failed ");
		}
		return responseEntity;
	}
}
