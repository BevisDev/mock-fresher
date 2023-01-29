package fa.training.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import fa.training.model.ClassBatchDetailDto;
import fa.training.model.ClassBatchDto;
import fa.training.model.ClassRequestInfoDto;
import fa.training.model.ResponseObject;
import fa.training.model.TraineeClassDto;
import fa.training.service.BudgetService;
import fa.training.service.ClassAdminService;
import fa.training.service.ClassBatchService;
import fa.training.service.ClassBatchTypeService;
import fa.training.service.ClassTraineeService;
import fa.training.service.DeliveryTypeService;
import fa.training.service.EventCategoryService;
import fa.training.service.FormatTypeService;
import fa.training.service.LocationService;
import fa.training.service.LoggedService;
import fa.training.service.PositionService;
import fa.training.service.ScopeService;
import fa.training.service.StatusInClassService;
import fa.training.service.SubSubjectTypeService;
import fa.training.service.SubjectTypeService;
import fa.training.service.SupplierPartnerService;
import fa.training.service.TraineeProfileService;
import fa.training.service.TrainerService;
import fa.training.util.CLASS_STATUS;
import fa.training.util.PaginationResult;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("class")
@Slf4j
public class ClassController {

	@Autowired
	private ClassTraineeService classTraineeService;

	@Autowired
	private LocationService locationService;
	@Autowired
	private BudgetService budgetService;
	@Autowired
	ClassAdminService adminService;
	@Autowired
	private DeliveryTypeService deliveryTypeService;
	@Autowired
	private SubjectTypeService subjectTypeService;
	@Autowired
	private SubSubjectTypeService subSubjectTypeService;
	@Autowired
	private ScopeService scopeService;
	@Autowired
	private SupplierPartnerService supplierPartnerService;
	@Autowired
	private FormatTypeService formatTypeService;
	@Autowired
	private TrainerService trainerService;
	@Autowired
	ClassBatchService classBatchService;
	@Autowired
	private EventCategoryService eventCategoryService;
	@Autowired
	private ClassBatchService classService;
	@Autowired
	private ClassBatchTypeService classTypeService;
	@Autowired
	private PositionService positionService;
	@Autowired
	private TraineeProfileService traineeProfileService;
	@Autowired
	private StatusInClassService statusService;
	@Autowired
	private LoggedService loggedService;

	@GetMapping("add")
	public String addClass(Model model, @ModelAttribute("message") String message) {
		String url = "/Error/500.html";
		try {
			model.addAttribute("locations", locationService.getAllLocation());
			model.addAttribute("budgets", budgetService.getAllBudget());
			model.addAttribute("classAdmins", adminService.getAllClassAdmin());
			model.addAttribute("deliveryTypes", deliveryTypeService.getAllDeliveryType());
			model.addAttribute("subjectTypes", subjectTypeService.getAllSubjectType());
			model.addAttribute("subSubjectTypes", subSubjectTypeService.getAllSubSubjectType());
			model.addAttribute("scopes", scopeService.getAllScope());
			model.addAttribute("formatTypes", formatTypeService.getAllFormatType());
			model.addAttribute("supplierPartners", supplierPartnerService.getAllSupplierPartner());
			model.addAttribute("trainers", trainerService.getAllTrainer());
			model.addAttribute("eventCateogries", eventCategoryService.getAllEventCategory());
			model.addAttribute("classTypes", classTypeService.getAllClassBatchType());
			model.addAttribute("positions", positionService.getAllPosition());
			model.addAttribute("classBatch", new ClassBatchDetailDto());
			model.addAttribute("message", message);
			url = "class/create-class";
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Load data for add class failed");
		}
		return url;
	}

	@GetMapping("edit/{id}")
	public String editClass(Model model, @ModelAttribute("message") String message, @PathVariable Long id) {
		String url = "/Error/500.html";
		try {
			model.addAttribute("locations", locationService.getAllLocation());
			model.addAttribute("budgets", budgetService.getAllBudget());
			model.addAttribute("classAdmins", adminService.getAllClassAdmin());
			model.addAttribute("deliveryTypes", deliveryTypeService.getAllDeliveryType());
			model.addAttribute("subjectTypes", subjectTypeService.getAllSubjectType());
			model.addAttribute("subSubjectTypes", subSubjectTypeService.getAllSubSubjectType());
			model.addAttribute("scopes", scopeService.getAllScope());
			model.addAttribute("formatTypes", formatTypeService.getAllFormatType());
			model.addAttribute("supplierPartners", supplierPartnerService.getAllSupplierPartner());
			model.addAttribute("trainers", trainerService.getAllTrainer());
			model.addAttribute("eventCateogries", eventCategoryService.getAllEventCategory());
			model.addAttribute("classTypes", classTypeService.getAllClassBatchType());
			model.addAttribute("positions", positionService.getAllPosition());
			model.addAttribute("classBatch", classService.getDetailClassById(id));
			model.addAttribute("message", message);
			url = "class/edit-class";
		} catch (Exception e) {
			log.error("Load data for add class failed");
		}
		return url;
	}

	@PostMapping("edit")
	public RedirectView editClass(@ModelAttribute("classBatch") ClassBatchDetailDto classBatchDetailDto,
			RedirectAttributes attributes) {
		String message = "Failed to update class";
		try {
			message = classService.updateDetailClassById(classBatchDetailDto.getClassId(), classBatchDetailDto);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Update class failed");
		}
		attributes.addFlashAttribute("message", message);
		return new RedirectView("/class/edit/" + classBatchDetailDto.getClassId());
	}

	@PutMapping("cancel")
	@ResponseBody
	public String cancelClasses(@RequestBody List<Long> classIds) {
		String message = "Failed to update class";
		try {
			message = classService.cancelClassesByIds(classIds);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Update class failed");
		}
		return message;
	}

	@PutMapping("/finish")
	@ResponseBody
	public String finishClass(@RequestBody List<Long> classIds) {
		String message = "Finish class failed";
		try {
			message = classService.finishClassById(classIds);
		} catch (Exception e) {
			log.error("Finish class failed");
		}
		return message;
	}

	@PutMapping("submit")
	@ResponseBody
	public String subitClass(@RequestParam Long classId) {
		String message = "Failed to submit class";
		try {
			message = classService.submitClassById(classId);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Update class failed");
		}
		return message;
	}

	@PutMapping("approve")
	@ResponseBody
	public String approveSubmittedClass(@RequestParam Long classId) {
		String message = "Failed to approve class";
		try {
			message = classService.approveSubmittedClass(classId);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Update class failed");
		}
		return message;
	}

	@PutMapping("reject")
	@ResponseBody
	public String rejectSubmittedClass(@RequestBody ClassBatchDto classInfo) {
		String message = "Failed to reject class";
		try {
			message = classService.rejectSubmittedClass(classInfo);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Update class failed");
		}
		return message;
	}

	@PostMapping("add")
	public RedirectView addClass(@ModelAttribute("classBatch") ClassBatchDetailDto classBatchDetailDto,
			RedirectAttributes attributes) {
		String message = "Failed to add class";
		try {
			message = classService.addClass(classBatchDetailDto);
		} catch (Exception e) {
			log.error("Add class failed");
		}
		attributes.addFlashAttribute("message", message);
		return new RedirectView("/class/add");

	}

	@GetMapping("view/{id}")
	public String viewDetailClass(Model model, @PathVariable Long id, @ModelAttribute("message") String message) {
		String url = "/Error/500.html";
		try {
			model.addAttribute("classBatch", classService.getDetailClassById(id));
			model.addAttribute("trainees", traineeProfileService.getTraineeProfileByClassId(id, 0, 10));
			model.addAttribute("traineesNotIn", classTraineeService.getTraineeNotInClass(id, 0, 10));
			model.addAttribute("statusInClass", statusService.getAllStatus());
			model.addAttribute("message", message);
			url = "class/view-class";
		} catch (Exception e) {
			log.error("Get detail class by class id failed");
		}
		return url;
	}

	@GetMapping("/view/{id}/trainee")
	public String viewClassTrainee(Model model, @PathVariable Long id, @RequestParam(defaultValue = "0") Integer offSet,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		model.addAttribute("trainees", traineeProfileService.getTraineeProfileByClassId(id, offSet, pageSize));
		return "/class/trainee-profile-page";
	}

	@GetMapping("/view/{id}/trainee-not-in")
	public String viewClassTraineeNotIn(Model model, @PathVariable Long id,
			@RequestParam(defaultValue = "0") Integer offSet, @RequestParam(defaultValue = "10") Integer pageSize) {
		model.addAttribute("traineesNotIn", classTraineeService.getTraineeNotInClass(id, offSet, pageSize));
		return "/class/trainee-profile-not-in-page";
	}

	@PostMapping("request-info")
	@ResponseBody
	public String requestForMoreInfo(@RequestBody ClassRequestInfoDto classRequestInfoDto,
			RedirectAttributes attributes) {
		String message = "Request failed";
		try {
			message = classService.requestMoreInfo(classRequestInfoDto);
		} catch (Exception e) {
			log.error("Request for more class info failed");
		}
		return message;
	}

	@PostMapping("remove-trainee")
	@ResponseBody
	public String removeTraineeFromClass(@RequestBody TraineeClassDto traineeClassDto) {
		String message = "Remove trainee from class failed";
		try {
			message = classService.removeTraineeFromClass(traineeClassDto);
		} catch (Exception e) {
			log.error("Remove trainee from class failed");
		}
		return message;
	}

	@GetMapping("")
	public String getAllClasses(Model model,
			@RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "2", required = false) Integer pageSize,
			@RequestBody(required = false) ClassBatchDto classBatchDto) {
		List<String> listOfStatus = Stream.of(CLASS_STATUS.values()).map(CLASS_STATUS::getStatus)
				.collect(Collectors.toList());
		model.addAttribute("locations", locationService.getAllLocation());
		model.addAttribute("listOfStatus", listOfStatus);
		model.addAttribute("classes", classBatchService.getAllClassBatch());
		PaginationResult rs = classBatchService.paginatedClassResult(pageSize, pageNum, classBatchDto);
		model.addAttribute("currentPage", rs.getCurrentPage());
		model.addAttribute("totalPages", rs.getTotalPages());
		model.addAttribute("totalItems", rs.getTotalItems());
		model.addAttribute("listOfClasses", rs.getListOfObjects());
		model.addAttribute("sizeOfPage", rs.getPageSize());
		model.addAttribute("allItems", rs.getAllItems());
		return "classManagement/ClassListing";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST, consumes = "application/json")
	public String getClassesBySearch(Model model,
			@RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "2", required = false) Integer pageSize,
			@RequestBody(required = false) ClassBatchDto classBatchDto) {
		List<String> listOfStatus = Stream.of(CLASS_STATUS.values()).map(CLASS_STATUS::getStatus)
				.collect(Collectors.toList());
		model.addAttribute("locations", locationService.getAllLocation());
		model.addAttribute("listOfStatus", listOfStatus);
		model.addAttribute("classes", classBatchService.getAllClassBatch());
		PaginationResult rs = classBatchService.paginatedClassResult(pageSize, pageNum, classBatchDto);
		model.addAttribute("currentPage", rs.getCurrentPage());
		model.addAttribute("totalPages", rs.getTotalPages());
		model.addAttribute("totalItems", rs.getTotalItems());
		model.addAttribute("listOfClasses", rs.getListOfObjects());
		model.addAttribute("sizeOfPage", rs.getPageSize());
		model.addAttribute("allItems", rs.getAllItems());
		model.addAttribute("researchData", classBatchDto);
		if (rs.getListOfObjects().size() == 0) {
			return "classManagement/EmptyClassDataArea";
		}
		return "classManagement/ClassDataArea";
	}

	@GetMapping("accept")
	public RedirectView acceptClass(@RequestParam(value = "classId", required = false) Long classId,
			RedirectAttributes attributes) {
		boolean isLogged = loggedService.isLogged();
		if (isLogged) {
			ResponseObject response = classBatchService.acceptClass(classId, CLASS_STATUS.PLANNED.getStatus());
			attributes.addFlashAttribute("message", response.getMessage());
			return new RedirectView("/class/view/" + classId);
		}
		return new RedirectView("/login");
	}

	@PostMapping("decline")
	public RedirectView declineClass(@RequestParam(value = "classId", required = false) Long classId,
			@RequestParam(value = "remarks", required = false) String remarks) {
		boolean isLogged = loggedService.isLogged();
		if (isLogged) {
			classBatchService.declineClass(classId, CLASS_STATUS.DECLINED.getStatus(), remarks);
			return new RedirectView("/class/view/" + classId);
		}
		return new RedirectView("/login");
	}

	@GetMapping("start")
	public RedirectView startClass(@RequestParam(value = "classId", required = false) Long classId,
			RedirectAttributes attributes) {
		boolean isLogged = loggedService.isLogged();
		if (isLogged) {
			ResponseObject response = classBatchService.startClass(classId, CLASS_STATUS.IN_PROCESS.getStatus());
			attributes.addFlashAttribute("message", response.getMessage());
			return new RedirectView("/class/view/" + classId);
		}
		return new RedirectView("/login");
	}

	@PutMapping("/close")
	@ResponseBody
	public String closeClass(@RequestBody List<Long> classIds) {
		String message = "Close class failed";
		try {
			message = classBatchService.closeClassById(classIds);
		} catch (Exception e) {
			log.error("Close class failed");
		}
		return message;
	}
}
