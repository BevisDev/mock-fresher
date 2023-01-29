package fa.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fa.training.model.LocationDto;
import fa.training.model.ResponseObject;
import fa.training.service.LocationService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/location")
@Slf4j
@CrossOrigin
public class LocationController {

	@Autowired
	private LocationService locationService;

	@GetMapping("")
	@PreAuthorize("hasAnyRole('ROLE_CLASSADMIN','ROLE_MANAGER','ROLE_REC','ROLE_MANAGER','ROLE_TRAINER','ROLE_SYSTEM')")
	public ResponseEntity<?> getAllLocation() {
		ResponseEntity<Object> responseEntity = ResponseEntity.badRequest().body("Get all location failed");
		try {
			List<LocationDto> locationDtos = locationService.getAllLocation();
			responseEntity = ResponseEntity.ok()
					.body(new ResponseObject(String.valueOf(HttpStatus.OK), "GET ALL LOCATION SUCCESS", locationDtos));
		} catch (Exception e) {
			log.error("Get all location failed");
		}
		return responseEntity;
	}

	@GetMapping("/get-location")
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_DELIVERYMANAGER','ROLE_SYSTEM', 'ROLE_REC', 'ROLE_CLASSADMIN')")
	public ResponseEntity<?> getLocationById(@RequestParam("id") Long id) {
		ResponseEntity<Object> responseEntity = ResponseEntity.badRequest().body("Get location by id is failed");
		try {
			LocationDto locationDto = locationService.getLocationById(id);
			responseEntity = ResponseEntity.ok().body(locationDto);
		} catch (Exception e) {
			log.error("Get location by id is failed ");
		}
		return responseEntity;
	}
}
