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

import fa.training.model.BudgetDto;
import fa.training.model.ResponseObject;
import fa.training.service.BudgetService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/budget")
@Slf4j
@CrossOrigin
public class BudgetController {

	@Autowired
	private BudgetService budgetService;
	
	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_DELIVERYMANAGER','ROLE_SYSTEM')")
	public ResponseEntity<?> getAllBudget()
	{
		ResponseEntity<Object> responseEntity = ResponseEntity.badRequest().body("Get all budget failed");
		try
		{
			List<BudgetDto> budgetDtos = budgetService.getAllBudget();
			responseEntity = ResponseEntity.ok().body(new ResponseObject(String.valueOf(HttpStatus.OK), "Get all budget success", budgetDtos));
		}catch (Exception e) {
			log.error("Get all budget failed ");
		}
		return responseEntity;
	}
}
