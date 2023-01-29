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
import fa.training.model.FormatTypeDto;
import fa.training.service.FormatTypeService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/format-type")
@CrossOrigin
@Slf4j
public class FormatTypeController {
	@Autowired
	private FormatTypeService formatTypeService;

	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_DELIVERYMANAGER','ROLE_SYSTEM','ROLE_CLASSADMIN')")
	public ResponseEntity<?> getAllFormatType() {
		ResponseEntity<Object> responseEntity = ResponseEntity.badRequest().body("Get all format type failed");
		try {
			List<FormatTypeDto> formatTypeDtos = formatTypeService.getAllFormatType();
			responseEntity = ResponseEntity.ok().body(
					new ResponseObject(String.valueOf(HttpStatus.OK), "Get all format type success", formatTypeDtos));
		} catch (Exception e) {
			log.error("Get all format type failed ");
		}
		return responseEntity;
	}
}
