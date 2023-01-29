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

import fa.training.model.FacultyDto;
import fa.training.model.ResponseObject;
import fa.training.service.FacultyService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/api/faculty")
@Slf4j
@CrossOrigin
public class FacultyController {
	@Autowired
	private FacultyService facultyService;

	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_DELIVERYMANAGER','ROLE_SYSTEM','ROLE_CLASSADMIN')")
	public ResponseEntity<?> getAllFaculties() {
		ResponseEntity<Object> responseEntity = ResponseEntity.badRequest().body("Get all faculties type is failed");
		try {
			List<FacultyDto> facultyDtos = facultyService.getAllFaculties();
			responseEntity = ResponseEntity.ok().body(
					new ResponseObject(String.valueOf(HttpStatus.OK), "Get all faculties is succeed", facultyDtos));
		} catch (Exception e) {
			log.error("Get all faculties is failed ");
		}
		return responseEntity;
	}

	@GetMapping("/get-faculty")
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_DELIVERYMANAGER','ROLE_SYSTEM', 'ROLE_REC', 'ROLE_CLASSADMIN')")
	public ResponseEntity<?> getFacultyById(@RequestParam("id") Long id) {
		ResponseEntity<Object> responseEntity = ResponseEntity.badRequest().body("Get faculty by id is failed");
		try {
			FacultyDto facultyDto = facultyService.getFacultyById(id);
			responseEntity = ResponseEntity.ok().body(facultyDto);
		} catch (Exception e) {
			log.error("Get faculty by id is failed ");
		}
		return responseEntity;
	}
}
