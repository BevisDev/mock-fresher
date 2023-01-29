package fa.training.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fa.training.model.ResponseObject;
import fa.training.model.TraineeClassDto;
import fa.training.model.TraineeProfileDto;
import fa.training.service.TraineeClassService;
import fa.training.service.TraineeProfileService;
import fa.training.util.MESSAGE;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/class")
@Slf4j
@CrossOrigin
public class ClassTraineeController {
	@Autowired
	private TraineeProfileService traineeProfileService;
	
	@Autowired
	private TraineeClassService traineeClassService;
	
	
	@GetMapping("/{classId}/trainee")
	@PreAuthorize("hasAnyRole('ROLE_CLASSADMIN','ROLE_MANAGER','ROLE_DELIVERYMANAGER','ROLE_SYSTEM')")
	public ResponseEntity<?> getTraineeNotInClass(@PathVariable Long classId,
			@RequestParam (defaultValue = "0") Integer offset, @RequestParam (defaultValue = "10") Integer pageSize){
		ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		try {
			Page<TraineeProfileDto> traineeProfileDtos = traineeProfileService.getPaginationTraineeNotInClass(classId,offset,pageSize);
			responseEntity = ResponseEntity.ok().body(traineeProfileDtos);
		} catch (Exception e) {
			log.error("Get trainee not in class failed");
		}
		
		return responseEntity;
	}
	
	@PostMapping("/{classId}/trainee/add")
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_DELIVERYMANAGER','ROLE_SYSTEM')")
	public ResponseEntity<?> addTraineeToClass(@RequestBody TraineeClassDto traineeClassDto){
		ResponseEntity<Object> responseEntity = ResponseEntity.badRequest().body("Add trainee to class failed");
		try {
			traineeClassService.addTraineeToClass(traineeClassDto);
			responseEntity = ResponseEntity.ok().body(new ResponseObject(String.valueOf(HttpStatus.OK),"Add trainee to class success",null));
		} catch (Exception e) {
			log.error("Add trainee to class failed"); 
		}
		return responseEntity;
	}
	
	@PostMapping(value = "/{classId}/trainee/import",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_DELIVERYMANAGER','ROLE_SYSTEM')")
	public ResponseEntity<?> importTraineeToClass(@PathVariable Long classId , @RequestParam MultipartFile file)
	{
		ResponseEntity<Object> responseEntity = ResponseEntity.badRequest().body("Import trainee to class failed");
		try
		{
			boolean rs = traineeClassService.importTraineeToClass(classId,file);
			if(rs==true)
			{
				responseEntity = ResponseEntity.ok().body(new ResponseObject(String.valueOf(HttpStatus.OK),MESSAGE.MSG22.msg,null));
			}
		}
		catch (IllegalArgumentException e) {
			responseEntity = ResponseEntity.badRequest().body(e.getMessage());
		}
		catch (Exception e) {
			log.error("Import trainee failed");
		}
		return responseEntity;

	}
	
}
