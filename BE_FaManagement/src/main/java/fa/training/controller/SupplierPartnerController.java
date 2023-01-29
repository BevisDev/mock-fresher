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
import fa.training.model.SupplierPartnerDto;
import fa.training.service.SupplierPartnerService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/supplier-partner")
@Slf4j
@CrossOrigin
public class SupplierPartnerController {
	@Autowired
	private SupplierPartnerService supplierPartnerService;

	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_DELIVERYMANAGER','ROLE_SYSTEM','ROLE_CLASSADMIN')")
	public ResponseEntity<?> getAllSubjectType() {
		ResponseEntity<Object> responseEntity = ResponseEntity.badRequest().body("Get all supplier partner failed");
		try {
			List<SupplierPartnerDto> supplierPartnerDtos = supplierPartnerService.getAllSupplierPartner();
			responseEntity = ResponseEntity.ok().body(new ResponseObject(String.valueOf(HttpStatus.OK),
					"Get all supplier partner success", supplierPartnerDtos));
		} catch (Exception e) {
			log.error("Get all supplier partner failed ");
		}
		return responseEntity;
	}
}
