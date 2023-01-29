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

import fa.training.model.EntryTestDto;
import fa.training.model.ResponseObject;
import fa.training.service.EntryTestService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/api/entry-test")
@Slf4j
@CrossOrigin
public class EntryTestController {
	@Autowired
	private EntryTestService entryTestService;

	@GetMapping("/get-list-by-candidate-id")
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_DELIVERYMANAGER','ROLE_SYSTEM', 'ROLE_REC', 'ROLE_CLASSADMIN')")
	public ResponseEntity<?> getEntryTestListByCandidateId(@RequestParam("id") Long candidateId) {
		ResponseEntity<Object> responseEntity = ResponseEntity.badRequest().body("Get entry test list by id is failed");
		try {
			List<EntryTestDto> entryTestDTOList = entryTestService.getAllEntryTestsByCandidateId(candidateId);
			responseEntity = ResponseEntity.ok().body(new ResponseObject(String.valueOf(HttpStatus.OK),
					"Get entry test list by id is succeed", entryTestDTOList));
		} catch (Exception e) {
			log.error("Get entry test list by id is failed ");
		}
		return responseEntity;
	}
}
