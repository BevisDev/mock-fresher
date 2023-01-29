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

import fa.training.model.EventCategoryDto;
import fa.training.model.ResponseObject;
import fa.training.service.EventCategoryService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/event-category")
@CrossOrigin
@Slf4j
public class EventCategoryController {
	@Autowired
	private EventCategoryService eventCategoryService;

	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_DELIVERYMANAGER','ROLE_SYSTEM','ROLE_CLASSADMIN')")
	public ResponseEntity<?> getAllEventCategory() {
		ResponseEntity<Object> responseEntity = ResponseEntity.badRequest().body("Get all event category failed");
		try {
			List<EventCategoryDto> eventCategoryDtos = eventCategoryService.getAllEventCategory();
			responseEntity = ResponseEntity.ok().body(new ResponseObject(String.valueOf(HttpStatus.OK),
					"Get all event category success", eventCategoryDtos));
		} catch (Exception e) {
			log.error("Get all event category failed ");
		}
		return responseEntity;
	}
}
