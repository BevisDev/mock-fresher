package fa.training.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fa.training.model.ClassBatchDetailDto;
import fa.training.model.ClassBatchDto;
import fa.training.model.ClassRequestInfoDto;
import fa.training.model.ResponseObject;
import fa.training.model.TraineeClassDto;
import fa.training.model.TraineeProfileDto;
import fa.training.service.ClassBatchService;
import fa.training.service.TraineeProfileService;
import fa.training.util.CLASS_STATUS;
import fa.training.util.MESSAGE;
import fa.training.util.PaginationResult;
import fa.training.util.SearchRequest;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/classBatch")
@CrossOrigin
@Slf4j
public class ClassBatchController {

	@Autowired
	private ClassBatchService classBatchService;
	@Autowired
	private TraineeProfileService traineeProfileService;

	@PostMapping("/add")
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_DELIVERYMANAGER','ROLE_SYSTEM')")
	public ResponseEntity<?> addClass(@RequestBody @Valid ClassBatchDetailDto classBatchDetailDto) {
		ResponseEntity<Object> responseEntity = ResponseEntity.badRequest().body("Add class failed");
		try {
			classBatchService.addClass(classBatchDetailDto);
			responseEntity = ResponseEntity.ok()
					.body(new ResponseObject(String.valueOf(HttpStatus.OK), MESSAGE.MSG23.msg, null));
		} catch (Exception e) {
			log.error("Add class failed");
		}
		return responseEntity;
	}

	@GetMapping("")
	@PreAuthorize("hasAnyRole('ROLE_CLASSADMIN','ROLE_MANAGER','ROLE_REC','ROLE_DELIVERYMANAGER','ROLE_TRAINER','ROLE_SYSTEM')")
	public ResponseEntity<?> getAllClass() {
		ResponseEntity<Object> responseEntity = ResponseEntity.badRequest().body("Get all class batch failed");
		try {
			List<ClassBatchDto> listClassBatchDto = classBatchService.getAllClassBatch();
			responseEntity = ResponseEntity.ok().body(
					new ResponseObject(String.valueOf(HttpStatus.OK), "GET ALL CLASSBATCH SUCCESS", listClassBatchDto));
		} catch (Exception e) {
			log.error("Get all class batch failed");
		}
		return responseEntity;

	}

//	@GetMapping("/num/{pageNum}/size/{pageSize}")
//	@PreAuthorize("hasAnyRole('ROLE_CLASSADMIN','ROLE_MANAGER','ROLE_REC','ROLE_MANAGER','ROLE_TRAINER','ROLE_SYSTEM')")
//	public ResponseEntity<?> getAllPaginatedClassBatch(@PathVariable(name = "pageNum") int pageNum,
//			@PathVariable(name = "pageSize") int pageSize) {
//		ResponseEntity<Object> responseEntity = ResponseEntity.badRequest().body("Get all class batch failed");
//		try {
//			PaginationResult result = classBatchService.paginatedClassResult(pageSize, pageNum);
//			responseEntity = ResponseEntity.ok()
//					.body(new ResponseObject(String.valueOf(HttpStatus.OK), "GET ALL CLASSBATCH SUCCESS", result));
//		} catch (Exception e) {
//			log.error("Get all class batch failed");
//		}
//		return responseEntity;
//
//	}

//	@GetMapping("/search")
//	@PreAuthorize("hasAnyRole('ROLE_CLASSADMIN','ROLE_MANAGER','ROLE_REC','ROLE_MANAGER','ROLE_TRAINER','ROLE_SYSTEM')")
//	public ResponseEntity<?> getListClassBatchBySearch(@RequestBody(required = false) SearchRequest searchRequest) {
//		ResponseEntity<Object> responseEntity = ResponseEntity.badRequest().body("Get list of class batch failed");
//		try {
//			List<ClassBatchDto> listClassBatchDto = classBatchService.getListClassBatchBySearch(searchRequest);
//			responseEntity = ResponseEntity.ok().body(
//					new ResponseObject(String.valueOf(HttpStatus.OK), "GET ALL CLASSBATCH SUCCESS", listClassBatchDto));
//		} catch (Exception e) {
//			log.error("Get list of class batch failed");
//		}
//		return responseEntity;
//
//	}

//	@GetMapping("/search/num/{pageNum}/size/{pageSize}")
//	@PreAuthorize("hasAnyRole('ROLE_CLASSADMIN','ROLE_MANAGER','ROLE_REC','ROLE_MANAGER','ROLE_TRAINER','ROLE_SYSTEM')")
//	public ResponseEntity<?> getListPaginatedClassBatchBySearch(
//			@RequestBody(required = false) SearchRequest searchRequest, @PathVariable(name = "pageNum") int pageNum,
//			@PathVariable(name = "pageSize") int pageSize) {
//		ResponseEntity<Object> responseEntity = ResponseEntity.badRequest().body("Get list of class batch failed");
//		try {
//			PaginationResult result = classBatchService.paginatedClassResultAfterSearch(searchRequest, pageSize,
//					pageNum);
//			responseEntity = ResponseEntity.ok()
//					.body(new ResponseObject(String.valueOf(HttpStatus.OK), "GET ALL CLASSBATCH SUCCESS", result));
//		} catch (Exception e) {
//			log.error("Get list of class batch failed");
//		}
//		return responseEntity;
//
//	}
//	
	@PutMapping("/search")
	@PreAuthorize("hasAnyRole('ROLE_CLASSADMIN','ROLE_MANAGER','ROLE_REC','ROLE_MANAGER','ROLE_TRAINER','ROLE_SYSTEM')")
	public ResponseEntity<?> getListPaginatedClassBatchBySearchOption(
			@RequestBody(required = false) SearchRequest searchRequest,
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		ResponseEntity<Object> responseEntity = ResponseEntity.badRequest().body("Get list of class batch failed");
		try {
			PaginationResult result = classBatchService.paginatedClassResultAfterSearch(searchRequest, pageSize,
					pageNum);
			responseEntity = ResponseEntity.ok()
					.body(new ResponseObject(String.valueOf(HttpStatus.OK), "GET ALL CLASSBATCH SUCCESS", result));
		} catch (Exception e) {
			log.error("Get list of class batch failed");
		}
		return responseEntity;
	}

	@GetMapping("/view/{id}")
	@PreAuthorize("hasAnyRole('ROLE_CLASSADMIN','ROLE_MANAGER','ROLE_DELIVERYMANAGER','ROLE_REC','ROLE_TRAINER','ROLE_SYSTEM')")
	public ResponseEntity<?> getDetailClassBatchById(@PathVariable Long id) {
		ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		try {
			ClassBatchDetailDto classBatchDetailDto = classBatchService.getDetailClassById(id);
			responseEntity = new ResponseEntity<Object>(classBatchDetailDto, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Get detail class failed");
		}
		return responseEntity;
	}

	@GetMapping("/view/{id}/trainee")
	@PreAuthorize("hasAnyRole('ROLE_CLASSADMIN','ROLE_MANAGER','ROLE_REC','ROLE_DELIVERYMANAGER','ROLE_TRAINER','ROLE_SYSTEM')")
	public ResponseEntity<?> getAllTraineeByClassId(@PathVariable Long id,
			@RequestParam(defaultValue = "0") Integer offset, @RequestParam(defaultValue = "1") Integer pageSize) {
		ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		try {
			Page<TraineeProfileDto> traineeProfileDtos = traineeProfileService.getPanigationTraineeByClassId(id, offset,
					pageSize);
			responseEntity = new ResponseEntity<Object>(traineeProfileDtos, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Get all trainee by class failed");
		}
		return responseEntity;
	}

	@PutMapping("/edit/{id}")
	@PreAuthorize("hasAnyRole('ROLE_SYSTEM','ROLE_MANAGER','ROLE_DELIVERYMANAGER','ROLE_CLASSADMIN')")
	public ResponseEntity<?> updateClassBatch(@PathVariable Long id,
			@RequestBody @Valid ClassBatchDetailDto classBatchDetailDto) {
		ResponseEntity<Object> responseEntity = ResponseEntity.badRequest().body("Update class failed");
		try {
			classBatchService.updateClassBatch(id, classBatchDetailDto);
			responseEntity = ResponseEntity.ok()
					.body(new ResponseObject(String.valueOf(HttpStatus.OK), MESSAGE.MSG22.msg, null));
		} catch (Exception e) {
			log.error("Update class failed");
		}
		return responseEntity;
	}

	@PutMapping("/cancel")
	@PreAuthorize("hasAnyRole('ROLE_SYSTEM','ROLE_MANAGER','ROLE_DELIVERYMANAGER')")
	public ResponseEntity<?> cancelClassBatch(@RequestBody List<Long> classIds) {
		ResponseEntity<Object> responseEntity = ResponseEntity.badRequest().body("Cancel class failed");
		try {
			classBatchService.cancelClassBatch(classIds);
			responseEntity = ResponseEntity.ok()
					.body(new ResponseObject(String.valueOf(HttpStatus.OK), MESSAGE.MSG22.msg, null));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Update class failed");
		}
		return responseEntity;
	}

	@PutMapping("/submit")
	@PreAuthorize("hasAnyRole('ROLE_SYSTEM','ROLE_MANAGER','ROLE_DELIVERYMANAGER','ROLE_CLASSADMIN')")
	public ResponseEntity<?> submitClassBatch(@RequestParam Long classId) {
		ResponseEntity<Object> responseEntity = ResponseEntity.badRequest().body("Submitting class failed");
		try {
			classBatchService.submitClassBatch(classId);
			responseEntity = ResponseEntity.ok()
					.body(new ResponseObject(String.valueOf(HttpStatus.OK), MESSAGE.MSG26.msg, null));
		} catch (Exception e) {
			log.error("Update class failed");
		}
		return responseEntity;
	}

	@PutMapping("/approve")
	@PreAuthorize("hasAnyRole('ROLE_SYSTEM','ROLE_MANAGER','ROLE_DELIVERYMANAGER')")
	public ResponseEntity<?> approveSumittedClass(@RequestParam Long classId) {
		ResponseEntity<Object> responseEntity = ResponseEntity.badRequest().body("Cancel class failed");
		try {
			classBatchService.approveSubmittedClassBatch(classId);
			responseEntity = ResponseEntity.ok()
					.body(new ResponseObject(String.valueOf(HttpStatus.OK), MESSAGE.MSG27.msg, null));
		} catch (Exception e) {
			log.error("Update class failed");
		}
		return responseEntity;
	}

	@PutMapping("/reject")
	@PreAuthorize("hasAnyRole('ROLE_SYSTEM','ROLE_MANAGER','ROLE_DELIVERYMANAGER')")
	public ResponseEntity<?> rejectSubmittedClassBatch(@RequestBody ClassBatchDto classInfo) {
		ResponseEntity<Object> responseEntity = ResponseEntity.badRequest().body("Reject class failed");
		try {
			classBatchService.rejectSubmittedClassBatch(classInfo);

			responseEntity = ResponseEntity.ok()
					.body(new ResponseObject(String.valueOf(HttpStatus.OK), MESSAGE.MSG22.msg, null));
		} catch (Exception e) {
			log.error("Update class failed");
		}
		return responseEntity;
	}

	@PostMapping("/request-info")
	@PreAuthorize("hasAnyRole('ROLE_SYSTEM','ROLE_MANAGER','ROLE_DELIVERYMANAGER')")
	public ResponseEntity<?> requestForMoreInfo(@RequestBody ClassRequestInfoDto classRequestInfoDto) {
		ResponseEntity<Object> responseEntity = ResponseEntity.badRequest().body("Request for more class info failed");
		try {
			classBatchService.requestForMoreInfo(classRequestInfoDto);
			responseEntity = ResponseEntity.ok()
					.body(new ResponseObject(String.valueOf(HttpStatus.OK), "Request successfully", null));
		} catch (Exception e) {
			log.error("Request for more class info failed");
		}
		return responseEntity;
	}

	@PostMapping("/remove-trainee")
	@PreAuthorize("hasAnyRole('ROLE_SYSTEM','ROLE_MANAGER','ROLE_DELIVERYMANAGER')")
	public ResponseEntity<?> removeTrainee(@RequestBody TraineeClassDto traineeClassDto) {
		ResponseEntity<Object> responseEntity = ResponseEntity.badRequest()
				.body("Remove trainee from class info failed");
		try {
			classBatchService.removeTraineeFromClass(traineeClassDto);
			responseEntity = ResponseEntity.ok().body(
					new ResponseObject(String.valueOf(HttpStatus.OK), "Remove trainee from class successfully", null));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Remove trainee from class info failed");
		}
		return responseEntity;
	}

	@PutMapping("/finish")
	@PreAuthorize("hasAnyRole('ROLE_SYSTEM','ROLE_MANAGER','ROLE_DELIVERYMANAGER','ROLE_CLASSADMIN')")
	public ResponseEntity<?> finishClass(@RequestBody List<Long> classIds) {
		ResponseEntity<Object> responseEntity = ResponseEntity.badRequest().body("Finish class failed");
		try {
			classBatchService.finishClass(classIds);
			responseEntity = ResponseEntity.ok()
					.body(new ResponseObject(String.valueOf(HttpStatus.OK), MESSAGE.MSG30.getResponse(), null));
		} catch (Exception e) {
			log.error("Finish class failed");
		}
		return responseEntity;
	}

	@PostMapping("/accept")
	@PreAuthorize("hasAnyRole('ROLE_CLASSADMIN','ROLE_MANAGER')")
	public ResponseEntity<?> acceptClass(@RequestBody Map<String, String> params) {
		String newStatus = params.get("newStatus");
		Long classId = Long.valueOf(params.get("classId"));
		boolean isAccept = classBatchService.changeStatus(CLASS_STATUS.PLANNING.get(), newStatus, classId);
		if (isAccept) {
			return ResponseEntity.ok()
					.body(new ResponseObject(String.valueOf(HttpStatus.OK), MESSAGE.MSG28.getResponse(), true));
		}
		return ResponseEntity.ok().body(
				new ResponseObject(String.valueOf(HttpStatus.EXPECTATION_FAILED), MESSAGE.MSG37.getResponse(), false));
	}

	@PostMapping("/decline")
	@PreAuthorize("hasAnyRole('ROLE_CLASSADMIN','ROLE_MANAGER')")
	public ResponseEntity<?> declineClass(@RequestBody Map<String, String> params) {
		String newStatus = params.get("newStatus");
		String remarks = params.get("remarks");
		Long classId = Long.valueOf(params.get("classId"));
		boolean isDecline = classBatchService.changeStatusAndRemarks(CLASS_STATUS.PLANNING.get(), newStatus, classId,
				remarks);
		if (isDecline) {
			return ResponseEntity.ok().body(new ResponseObject(String.valueOf(HttpStatus.OK), "", true));
		}
		return ResponseEntity.ok().body(new ResponseObject(String.valueOf(HttpStatus.EXPECTATION_FAILED), "", false));
	}

	@PostMapping("/start")
	@PreAuthorize("hasAnyRole('ROLE_CLASSADMIN','ROLE_MANAGER', 'ROLE_DELIVERYMANAGER', 'ROLE_CLASSADMIN')")
	public ResponseEntity<?> startClass(@RequestBody Map<String, String> params) {
		String newStatus = params.get("newStatus");
		Long classId = Long.valueOf(params.get("classId"));
		boolean isStart = classBatchService.changeStatus(CLASS_STATUS.PLANNED.get(), newStatus, classId);
		if (isStart) {
			return ResponseEntity.ok()
					.body(new ResponseObject(String.valueOf(HttpStatus.OK), MESSAGE.MSG29.getResponse(), true));
		}
		return ResponseEntity.ok().body(
				new ResponseObject(String.valueOf(HttpStatus.EXPECTATION_FAILED), MESSAGE.MSG38.getResponse(), false));
	}

	@PutMapping("/close")
	@PreAuthorize("hasAnyRole('ROLE_SYSTEM','ROLE_MANAGER','ROLE_DELIVERYMANAGER')")
	public ResponseEntity<?> closeClass(@RequestBody List<Long> classIds) {
		ResponseEntity<Object> responseEntity = ResponseEntity.badRequest().body("close class failed");
		try {
			classBatchService.closeClassBatch(classIds);
			responseEntity = ResponseEntity.ok()
					.body(new ResponseObject(String.valueOf(HttpStatus.OK), "Close class success", null));
		} catch (Exception e) {
			log.error("Close class failed");
		}
		return responseEntity;
	}
}
