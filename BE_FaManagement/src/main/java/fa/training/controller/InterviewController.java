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

import fa.training.model.InterviewDto;
import fa.training.model.ResponseObject;
import fa.training.service.InterviewService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/api/interview")
@Slf4j
@CrossOrigin
public class InterviewController {
	@Autowired
	private InterviewService interviewService;

	@GetMapping("/get-list-by-candidate-id")
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_DELIVERYMANAGER','ROLE_SYSTEM', 'ROLE_REC', 'ROLE_CLASSADMIN')")
	public ResponseEntity<?> getInterviewListByCandidateId(@RequestParam("id") Long candidateId) {
		ResponseEntity<Object> responseEntity = ResponseEntity.badRequest().body("Get interview list by id is failed");
		try {
			List<InterviewDto> interviewDTOList = interviewService.getAllInterviewsByCandidateId(candidateId);
			responseEntity = ResponseEntity.ok().body(new ResponseObject(String.valueOf(HttpStatus.OK),
					"Get interview list by id is succeed", interviewDTOList));
		} catch (Exception e) {
			log.error("Get interview list by id is failed ");
		}
		return responseEntity;
	}
}
