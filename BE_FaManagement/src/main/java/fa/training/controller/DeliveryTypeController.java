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
import fa.training.model.DeliveryTypeDto;
import fa.training.service.DeliveryTypeService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/delivery-type")
@Slf4j
@CrossOrigin
public class DeliveryTypeController {

	@Autowired
	private DeliveryTypeService deliveryTypeService;

	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_DELIVERYMANAGER','ROLE_SYSTEM','ROLE_CLASSADMIN')")
	public ResponseEntity<?> getAllSubjectType() {
		ResponseEntity<Object> responseEntity = ResponseEntity.badRequest().body("Get all delivery type failed");
		try {
			List<DeliveryTypeDto> deliveryTypeDtos = deliveryTypeService.getAllDeliveryType();
			responseEntity = ResponseEntity.ok().body(new ResponseObject(String.valueOf(HttpStatus.OK),
					"Get all delivery type success", deliveryTypeDtos));
		} catch (Exception e) {
			log.error("Get all delivery type failed ");
		}
		return responseEntity;
	}
}
