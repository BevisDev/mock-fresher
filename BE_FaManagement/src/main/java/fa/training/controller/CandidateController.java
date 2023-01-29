package fa.training.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fa.training.model.CandidateDto;
import fa.training.model.CandidateForm;
import fa.training.model.CandidateListPage;
import fa.training.model.ResponseObject;
import fa.training.model.RestCreateCandidate;
import fa.training.service.CandidateService;
import fa.training.util.CandidateValidation;
import fa.training.util.MESSAGE;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/candidate")
@CrossOrigin
@Slf4j
public class CandidateController {
	@Autowired
	private CandidateValidation candidateValidation;
	@Autowired
	private CandidateService candidateService;

	@PostMapping("/create")
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_REC','ROLE_SYSTEM')")
	public ResponseEntity<?> createCandidate(@RequestBody RestCreateCandidate restCreateCandidate) {
		MESSAGE isValid = candidateValidation.createCandidateValidation(restCreateCandidate.getCandidateForm());
		if (isValid == null) {
			boolean isCreate = candidateService.createCandidate(restCreateCandidate);
			if (isCreate) {
				return ResponseEntity.ok()
						.body(new ResponseObject(String.valueOf(HttpStatus.OK), MESSAGE.MSG23.getResponse(), true));
			}
			return ResponseEntity.ok().body(new ResponseObject(String.valueOf(HttpStatus.EXPECTATION_FAILED),
					MESSAGE.MSG31.getResponse(), false));
		}
		return ResponseEntity.ok()
				.body(new ResponseObject(String.valueOf(HttpStatus.EXPECTATION_FAILED), isValid.getResponse(), false));
	}

	@GetMapping("/get")
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_DELIVERYMANAGER','ROLE_SYSTEM', 'ROLE_REC', 'ROLE_CLASSADMIN')")
	public ResponseEntity<?> getCandidateById(@RequestParam("id") Long candidateId) {
		ResponseEntity<Object> responseEntity = ResponseEntity.badRequest().body("Get candidate by id is failed");
		try {
			CandidateDto candidateDto = candidateService.getCandidateById(candidateId);
			if (candidateDto != null) {
				responseEntity = ResponseEntity.ok().body(candidateDto);
			}
		} catch (Exception e) {
			log.error("Get candidate by id is failed ");
		}
		return responseEntity;
	}

	@GetMapping("/get-form")
	@PreAuthorize("hasAnyRole('ROLE_SYSTEM', 'ROLE_REC', 'ROLE_CLASSADMIN')")
	public ResponseEntity<?> getCandidateFormByCandidateId(@RequestParam("id") Long candidateId) {
		ResponseEntity<Object> responseEntity = ResponseEntity.badRequest().body("Get candidate form by id is failed");
		try {
			CandidateForm candidateForm = candidateService.getCandidateFormByCandidateId(candidateId);
			if (candidateForm != null) {
				responseEntity = ResponseEntity.ok().body(candidateForm);
			}
		} catch (Exception e) {
			log.error("Get candidate form by id is failed ");
		}
		return responseEntity;
	}

	@PostMapping("/update")
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_REC','ROLE_SYSTEM')")
	public ResponseEntity<?> updateCandidate(@RequestBody CandidateForm candidateForm) {
		MESSAGE isValid = candidateValidation.updateCandidateValidation(candidateForm);
		if (isValid == null) {
			boolean isUpdate = candidateService.updateCandidate(candidateForm);
			if (isUpdate) {
				return ResponseEntity.ok()
						.body(new ResponseObject(String.valueOf(HttpStatus.OK), MESSAGE.MSG22.getResponse(), true));
			}
			return ResponseEntity.ok().body(new ResponseObject(String.valueOf(HttpStatus.EXPECTATION_FAILED),
					MESSAGE.MSG32.getResponse(), false));
		}
		return ResponseEntity.ok()
				.body(new ResponseObject(String.valueOf(HttpStatus.EXPECTATION_FAILED), isValid.getResponse(), false));
	}

	@PostMapping("/delete")
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_REC','ROLE_SYSTEM')")
	public ResponseEntity<?> deleteCandidate(@RequestBody Long candidateId) {
		boolean isDelete = candidateService.deleteCandidate(candidateId);
		if (isDelete) {
			return ResponseEntity.ok()
					.body(new ResponseObject(String.valueOf(HttpStatus.OK), MESSAGE.MSG34.getResponse(), true));
		}
		return ResponseEntity.ok().body(
				new ResponseObject(String.valueOf(HttpStatus.EXPECTATION_FAILED), MESSAGE.MSG33.getResponse(), false));
	}

	@PostMapping("/delete-many")
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_REC','ROLE_SYSTEM')")
	public ResponseEntity<?> deleteManyCandidate(@RequestBody List<Long> canIds) {
		boolean isDelete = candidateService.deleteManyCandidate(canIds);
		if (isDelete) {
			return ResponseEntity.ok()
					.body(new ResponseObject(String.valueOf(HttpStatus.OK), MESSAGE.MSG34.getResponse(), true));
		}
		return ResponseEntity.ok().body(
				new ResponseObject(String.valueOf(HttpStatus.EXPECTATION_FAILED), MESSAGE.MSG33.getResponse(), false));
	}

	@PostMapping("/transfer")
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_REC','ROLE_SYSTEM')")
	public ResponseEntity<?> transferCandidate(@RequestBody Map<String, String> params) {
		Long candidateId = Long.valueOf(params.get("id"));
		String transferLocation = params.get("transferLocation");
		boolean isTransfer = candidateService.transferCandidate(candidateId, transferLocation);
		if (isTransfer) {
			return ResponseEntity.ok()
					.body(new ResponseObject(String.valueOf(HttpStatus.OK), MESSAGE.MSG35.getResponse(), true));
		}
		return ResponseEntity.ok().body(
				new ResponseObject(String.valueOf(HttpStatus.EXPECTATION_FAILED), MESSAGE.MSG36.getResponse(), false));
	}

	@GetMapping("/list")
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_DELIVERYMANAGER','ROLE_SYSTEM', 'ROLE_REC', 'ROLE_CLASSADMIN')")
	public ResponseEntity<?> getCandidateListPage(
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
			@RequestParam(value = "pageNumber", defaultValue = "1", required = false) int pageNumber) {
		ResponseEntity<Object> responseEntity = ResponseEntity.badRequest().body("Get all candidate list is failed");
		try {
			List<CandidateListPage> candidateListPages = candidateService.getCandidateList(pageSize, pageNumber);
			responseEntity = ResponseEntity.ok().body(new ResponseObject(String.valueOf(HttpStatus.OK),
					"Get all candidate list page is succeed", candidateListPages));
		} catch (Exception e) {
			log.error("Get all candidate list page is failed ");
		}
		return responseEntity;
	}

	@GetMapping("/get-all")
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_DELIVERYMANAGER','ROLE_SYSTEM', 'ROLE_REC', 'ROLE_CLASSADMIN')")
	public ResponseEntity<?> getAllCandidateAmount() {
		ResponseEntity<Object> responseEntity = ResponseEntity.badRequest().body("Get all candidate amount is failed");
		try {
			int amountCandidate = candidateService.getAllCandidateAmount();
			responseEntity = ResponseEntity.ok().body(amountCandidate);
		} catch (Exception e) {
			log.error("Get all candidate amount failed ");
		}
		return responseEntity;
	}
}
