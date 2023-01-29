package fa.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fa.training.entity.AllowanceGroup;
import fa.training.model.AllowanceGroupDto;
import fa.training.service.AllowanceGroupService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/api/allowance-group")
@Slf4j
@CrossOrigin
public class AllowanceGroupController {
	@Autowired
	private AllowanceGroupService allowanceGroupService;
	
	@GetMapping()
	public ResponseEntity<?> getAllAllowanceGroups(){
		ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		try {
			List<AllowanceGroupDto> allowanceGroups = allowanceGroupService.getAllAllowanceGroup();
			responseEntity = ResponseEntity.ok().body(allowanceGroups);
		} catch (Exception e) {
			log.error("Get all allowance group failed");
		}
		return responseEntity;
	}
	
	@GetMapping("/get-allowance-group")
	public ResponseEntity<?> getAllowanceGroupById(@RequestParam String group){
		ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		try {
			AllowanceGroupDto allowanceGroupDto = allowanceGroupService.getAllowanceById(group);
			responseEntity = ResponseEntity.ok().body(allowanceGroupDto);
		} catch (Exception e) {
			log.error("Get allowance group by id failed");
		}
		return responseEntity;
	}
}
