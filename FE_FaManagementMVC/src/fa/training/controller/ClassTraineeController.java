package fa.training.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.multipart.MultipartFile;


import fa.training.model.PageTraineeProfileDto;
import fa.training.model.TraineeClassDto;
import fa.training.service.ClassTraineeService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/class")
@Slf4j
public class ClassTraineeController {

	@Autowired
	private ClassTraineeService classTraineeService;

	@GetMapping("/{classId}/trainee-not-in")
	public String getTraineeNotInClass(Model model, @PathVariable Long classId,
			@RequestParam(defaultValue = "0") Integer offset, @RequestParam(defaultValue = "10") Integer pageSize) {
		String url = "Error/500.html";
		try {
			PageTraineeProfileDto traineeProfilePageDtos = classTraineeService.getTraineeNotInClass(classId, offset,
					pageSize);
			model.addAttribute("trainees", traineeProfilePageDtos);
			url = "/class/view-class";
		} catch (Exception e) {
			log.error("Get trainee not in class failed");
		}
		return url;
	}

	@PostMapping("/{classId}/trainee-not-in/add")
	@ResponseBody
	public String addTraineeToclass(Model model, @RequestBody TraineeClassDto traineeClassDto) {
		String message = "Add trainee to class failed";
		try {
			message = classTraineeService.addTraineeToClass(traineeClassDto);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Add trainee to class failed");
		}
		return message;
	}

	@PostMapping("/{classId}/import-trainee")
	@ResponseBody
	public String importTraineeToClass(@RequestParam MultipartFile file, @PathVariable Long classId) {
		String message = "Import trainee to class failed";
		try
		{
			message = classTraineeService.importTraineeToClass(file, classId);
		}catch(HttpStatusCodeException e)
		{
			message = e.getResponseBodyAsString();
		}
		catch (Exception e) {
			log.error("Import trainee to class failed");
		}
		return message;
	}
}
