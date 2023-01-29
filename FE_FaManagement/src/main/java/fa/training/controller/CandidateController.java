package fa.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import fa.training.model.CandidateDto;
import fa.training.model.CandidateForm;
import fa.training.model.EntryTestDto;
import fa.training.model.InterviewDto;
import fa.training.model.ResponseObject;
import fa.training.model.TraineeProfileDto;
import fa.training.service.CandidateService;
import fa.training.service.ChannelService;
import fa.training.service.EntryTestService;
import fa.training.service.FacultyService;
import fa.training.service.InterviewService;
import fa.training.service.LocationService;
import fa.training.service.LoggedService;
import fa.training.service.TraineeProfileService;
import fa.training.service.UniversityService;
import fa.training.util.CandidateStatusList;
import fa.training.util.PageSize;
import fa.training.util.TransferLocationList;

@Controller
@RequestMapping("candidate")
public class CandidateController {
	@Autowired
	private ChannelService channelService;
	@Autowired
	private FacultyService facultyService;
	@Autowired
	private UniversityService universityService;
	@Autowired
	private LocationService locationService;
	@Autowired
	private LoggedService loggedService;
	@Autowired
	private CandidateService candidateService;
	@Autowired
	private TraineeProfileService traineeProfileService;
	@Autowired
	private InterviewService interviewService;
	@Autowired
	private EntryTestService entryTestService;

	@GetMapping("create")
	public String getDataCandidateCreatePage(Model model) {
		boolean isLogged = loggedService.isLogged();
		if (isLogged) {
			shareDataCreateCandidatePage(model);
			return "candidate/create_candidate_profile";
		}
		return "redirect:/login";
	}

	@PostMapping("create")
	public String createCandidate(@ModelAttribute("candidate") CandidateForm candidateFormDto,
			@ModelAttribute("interview") InterviewDto interviewDto,
			@ModelAttribute("entryTest") EntryTestDto entryTestDto,
			@RequestParam(name = "dob", required = false) String dob,
			@RequestParam(name = "graYear", required = false) String graYear,
			@RequestParam(name = "appDate", required = false) String appDate,
			@RequestParam(name = "dateEntry", required = false) String dateEntry,
			@RequestParam(name = "dateInter", required = false) String dateInter,
			@RequestParam(name = "fileCV", required = false) MultipartFile fileCV, Model model) {
		boolean isLogged = loggedService.isLogged();
		if (isLogged) {
			ResponseObject response = candidateService.createCandidate(candidateFormDto, interviewDto, entryTestDto,
					dob, graYear, appDate, dateEntry, dateInter, fileCV);
			if ((boolean) response.getData()) {
				model.addAttribute("valid", response.getMessage());
			} else {
				model.addAttribute("invalid", response.getMessage());
			}
			shareDataCreateCandidatePage(model);
			return "candidate/create_candidate_profile";
		}
		return "redirect:/login";
	}

	@GetMapping("view")
	public String getDataCandidateInforPage(@RequestParam(value = "id", required = false) Long candidateId,
			@RequestParam(value = "invalid", required = false, defaultValue = "") String invalid, Model model) {
		boolean isLogged = loggedService.isLogged();
		if (isLogged) {
			CandidateDto candidateDto = candidateService.getCandidateById(candidateId);
			TraineeProfileDto traineeProfileDto = traineeProfileService
					.getTraineeProfileById(candidateDto.getProfileId());
			model.addAttribute("invalid", invalid);
			model.addAttribute("channel", channelService.getChannelById(candidateDto.getChannelId()));
			model.addAttribute("location", locationService.getLocationById(candidateDto.getLocationId()));
			model.addAttribute("candidate", candidateDto);
			model.addAttribute("trainee", traineeProfileDto);
			model.addAttribute("faculty", facultyService.getFacultyById(traineeProfileDto.getFacultyId()));
			model.addAttribute("university", universityService.getUniversityById(traineeProfileDto.getUniversityId()));
			model.addAttribute("interviews", interviewService.getAllInterviewsByCandidateId(candidateId));
			model.addAttribute("entryTests", entryTestService.getAllEntryTestDto(candidateId));
			model.addAttribute("transferLocations", TransferLocationList.TRANSFER_LOCATION_LIST);
			return "candidate/infor_candidate_profile";
		}
		return "redirect:/login";
	}

	@GetMapping("update")
	public String getDataCandidateUpdatePage(@RequestParam(value = "id", required = false) Long candidateId,
			Model model) {
		boolean isLogged = loggedService.isLogged();
		if (isLogged) {
			shareDataUpdateCandidatePage(model, candidateId);
			return "candidate/update_candidate_profile";
		}
		return "redirect:/login";
	}

	@PostMapping("update")
	public String updateCandidate(@ModelAttribute("canForm") CandidateForm canForm, Model model,
			@RequestParam(name = "graYear", required = false) String graYear,
			@RequestParam(name = "fileCV", required = false) MultipartFile fileCV,
			@RequestParam(name = "id", required = false) Long canId) {
		boolean isLogged = loggedService.isLogged();
		if (isLogged) {
			ResponseObject response = candidateService.updateCandidate(canForm, graYear, fileCV, canId);
			if ((boolean) response.getData()) {
				model.addAttribute("valid", response.getMessage());
			} else {
				model.addAttribute("invalid", response.getMessage());
			}
			shareDataUpdateCandidatePage(model, canId);
			return "candidate/update_candidate_profile";
		}
		return "redirect:/login";
	}

	@GetMapping("delete")
	public String deleteCandidate(@RequestParam(value = "id", required = false) Long candidateId) {
		boolean isLogged = loggedService.isLogged();
		if (isLogged) {
			ResponseObject response = candidateService.deleteCandidate(candidateId);
			if ((boolean) response.getData()) {
				return "redirect:/candidate/list?valid=" + response.getMessage();
			}
			return "redirect:/candidate/view?id=" + candidateId + "&invalid=" + response.getMessage();
		}
		return "redirect:/login";
	}

	@GetMapping("transfer")
	public String transferCandidate(@RequestParam(value = "id", required = false) Long candidateId,
			@RequestParam(value = "locationTransfer", required = false) String locationTransfer) {
		boolean isLogged = loggedService.isLogged();
		if (isLogged) {
			candidateService.transferCandidate(candidateId, locationTransfer);
			return "redirect:/candidate/view?id=" + candidateId;
		}
		return "redirect:/login";
	}

	@GetMapping("list")
	public String getDataCandidateListPage(
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
			@RequestParam(value = "valid", required = false, defaultValue = "") String valid,
			@RequestParam(value = "invalid", required = false, defaultValue = "") String invalid, Model model) {
		int amount = candidateService.getAllCandidateAmount();
		int amountOption = generateAmountOptionPage(amount, pageSize);
		model.addAttribute("valid", valid);
		model.addAttribute("invalid", invalid);
		model.addAttribute("candidates", candidateService.getCandidateList(pageSize, pageNumber));
		model.addAttribute("amount", amount);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("pageSizeList", PageSize.PAGE_SIZE_CANDIDATE_LIST);
		model.addAttribute("amountOption", amount > 0 ? amountOption : amountOption + 1);
		return "candidate/candidate_list";
	}

	@PostMapping("delete-many")
	public String deleteManyCandidate(@RequestParam(name = "checkDel", required = false) List<Long> canIds,
			Model model) {
		boolean isLogged = loggedService.isLogged();
		if (isLogged) {
			if (canIds != null) {
				ResponseObject response = candidateService.deleteManyCandidate(canIds);
				if ((boolean) response.getData()) {
					return "redirect:/candidate/list?valid=" + response.getMessage();
				} else {
					return "redirect:/candidate/list?invalid=" + response.getMessage();
				}
			}
			return "redirect:/candidate/list";
		}
		return "redirect:/login";
	}

	public void shareDataCreateCandidatePage(Model model) {
		CandidateForm newCandidateFormDto = new CandidateForm();
		newCandidateFormDto.setGender("Male");
		model.addAttribute("channels", channelService.getAllChannels());
		model.addAttribute("faculties", facultyService.getAllFaculties());
		model.addAttribute("universities", universityService.getAllUniversities());
		model.addAttribute("locations", locationService.getAllLocation());
		model.addAttribute("candidate", newCandidateFormDto);
		model.addAttribute("interview", new InterviewDto());
		model.addAttribute("entryTest", new EntryTestDto());
		model.addAttribute("resultsEntry", CandidateStatusList.CANDIDATE_RESULT);
		model.addAttribute("resultsInter", CandidateStatusList.CANDIDATE_RESULT);
	}

	public void shareDataUpdateCandidatePage(Model model, Long candidateId) {
		CandidateForm canForm = candidateService.getCandidateFormByCandidateId(candidateId);
		canForm.setInterviews(interviewService.getAllInterviewsByCandidateId(candidateId));
		canForm.setEntryTests(entryTestService.getAllEntryTestDto(candidateId));
		model.addAttribute("canForm", canForm);
		model.addAttribute("channels", channelService.getAllChannels());
		model.addAttribute("faculties", facultyService.getAllFaculties());
		model.addAttribute("universities", universityService.getAllUniversities());
		model.addAttribute("locations", locationService.getAllLocation());
		model.addAttribute("resultsEntry", CandidateStatusList.CANDIDATE_RESULT);
		model.addAttribute("resultsInter", CandidateStatusList.CANDIDATE_RESULT);
	}

	public int generateAmountOptionPage(int amount, int pageSize) {
		double result = (double) amount / pageSize;
		return (int) (Math.ceil(result) != Math.floor(result) ? result + 1 : result);
	}
}
