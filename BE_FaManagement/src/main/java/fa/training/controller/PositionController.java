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

import fa.training.model.PositionDto;
import fa.training.model.ResponseObject;
import fa.training.service.PositionService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/position")
@Slf4j
@CrossOrigin
public class PositionController {
	@Autowired
	private PositionService positionService;
	
	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_DELIVERYMANAGER','ROLE_SYSTEM')")
	public ResponseEntity<?> getAllPosition()
	{
		ResponseEntity<Object> responseEntity = ResponseEntity.badRequest().body("Get all position failed");
		try
		{
			List<PositionDto> positionDtos = positionService.getAllPosition();
			responseEntity = ResponseEntity.ok().body(new ResponseObject(String.valueOf(HttpStatus.OK), "Get all position success", positionDtos));
		}catch (Exception e) {
			log.error("Get all position failed ");
		}
		return responseEntity;
	}
}
