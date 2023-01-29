package fa.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fa.training.model.ResponseObject;
import fa.training.model.UniversityDto;
import fa.training.service.UniversityService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/api/university")
@Slf4j
@CrossOrigin
public class UniversityController {
	@Autowired
	private UniversityService universityService;

	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_DELIVERYMANAGER','ROLE_SYSTEM','ROLE_CLASSADMIN')")
	public ResponseEntity<?> getAllUniversities() {
		ResponseEntity<Object> responseEntity = ResponseEntity.badRequest().body("Get all universities type is failed");
		try {
			List<UniversityDto> universityDtos = universityService.getAllUniversities();
			responseEntity = ResponseEntity.ok().body(new ResponseObject(String.valueOf(HttpStatus.OK),
					"Get all universities is succeed", universityDtos));
		} catch (Exception e) {
			log.error("Get all universities is failed ");
		}
		return responseEntity;
	}

	@GetMapping("/get-university")
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_DELIVERYMANAGER','ROLE_SYSTEM', 'ROLE_REC', 'ROLE_CLASSADMIN')")
	public ResponseEntity<?> getUniversityById(@RequestParam("id") Long id) {
		ResponseEntity<Object> responseEntity = ResponseEntity.badRequest().body("Get university by id is failed");
		try {
			UniversityDto universityDto = universityService.getUniversityById(id);
			responseEntity = ResponseEntity.ok().body(universityDto);
		} catch (Exception e) {
			log.error("Get university by id is failed ");
		}
		return responseEntity;
	}
	
}
