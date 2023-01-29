package fa.training.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fa.training.model.FacultyDto;
import fa.training.model.PageTraineeProfileDto;
import fa.training.model.TraineeProfileDto;
import fa.training.model.UniversityDto;
import fa.training.service.FacultyService;
import fa.training.service.TraineeProfileService;
import fa.training.service.UniversityService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/trainee")
@Slf4j
public class TraineeController {
	
	@Autowired
	private TraineeProfileService traineeProfileService;
	
	@Autowired
	private UniversityService universityService;
	
	@Autowired
	private FacultyService facultyService;
	

	@GetMapping()
	public String getTraineeList(Model model, @RequestParam (defaultValue = "0") Integer offset, @RequestParam (defaultValue = "10") Integer pageSize) {
		String url = "/Error/500.html";
		try {
			PageTraineeProfileDto traineeProfileDtos = traineeProfileService.getTraineeList(offset,pageSize);
			model.addAttribute("trainees",traineeProfileDtos);
			url = "/trainee/trainee-list";
		} catch (Exception e) {
			log.error("Get data from trainee list failed");
		}
		return url;
	}
	
	@GetMapping("/trainee-profile")
	public String getTraineeInfoById(Model model,@RequestParam Long id) {
		String url = "Error/500.html";
		try {
			TraineeProfileDto traineeProfileDto = traineeProfileService.getTraineeProfileById(id);
			model.addAttribute("trainee",traineeProfileDto);
			url="/trainee/trainee-profile-view";
		} catch (Exception e) {
			log.error("Get data from trainee id failed");
		}
		return url;
	}
	
	@GetMapping("/trainee-infor")
	public String getTraineeProfileByAccount(Model model, @RequestParam("account") String account) {
		String url = "Error/500.html";
		try {
			TraineeProfileDto traineeProfileDto = traineeProfileService.getTraineeProfileByAccount(account);
			UniversityDto universityDto = universityService.getUniversityById(traineeProfileDto.getUniversityId());
			traineeProfileDto.setUniversityName(universityDto.getName());
			FacultyDto facultyDto = facultyService.getFacultyById(traineeProfileDto.getFacultyId());
			traineeProfileDto.setFacultyName(facultyDto.getName());
			model.addAttribute("trainee",traineeProfileDto);
			url="/trainee/trainee-profile-view";
		} catch (Exception e) {
			e.printStackTrace(); 
			log.error("Get data from trainee account failed");
		}
		return url;
	}
	
	@PostMapping("/delete-trainee-infor")
	@ResponseBody
	public String deleteTraineeProfileById(@RequestBody List<Long> traineeIds) {
		String message = "Delete trainee profile failed";
		try {
			message =traineeProfileService.deleteTraineeProfileById(traineeIds);
		
		} catch (Exception e) {
			log.error("Delete trainee failed");
		}
		return message;
	}
	
}
