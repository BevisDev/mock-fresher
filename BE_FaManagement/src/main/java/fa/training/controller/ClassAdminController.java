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

import fa.training.model.ClassAdminDto;
import fa.training.model.ResponseObject;
import fa.training.service.ClassAdminService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/class-admin")
@Slf4j
@CrossOrigin
public class ClassAdminController {

	@Autowired
	private ClassAdminService adminService;
	
	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_DELIVERYMANAGER','ROLE_SYSTEM','ROLE_CLASSADMIN')")
	public ResponseEntity<?> getAllClassAdmin()
	{
		ResponseEntity<Object> responseEntity = ResponseEntity.badRequest().body("Get all class admin failed");
		try
		{
			List<ClassAdminDto> adminDtos = adminService.getAllClassAdmin();
			responseEntity = ResponseEntity.ok().body(new ResponseObject(String.valueOf(HttpStatus.OK), "GET ALL CLASS ADMIN SUCCESS", adminDtos));
		}catch (Exception e) {
			log.error("Get all class admin failed ");
		}
		return responseEntity;
	}
}
