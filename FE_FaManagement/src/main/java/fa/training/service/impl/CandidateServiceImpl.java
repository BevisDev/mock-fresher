package fa.training.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import fa.training.model.CandidateDto;
import fa.training.model.CandidateForm;
import fa.training.model.CandidateListPage;
import fa.training.model.EntryTestDto;
import fa.training.model.InterviewDto;
import fa.training.model.ResponseObject;
import fa.training.model.RestCreateCandidate;
import fa.training.service.CandidateService;
import fa.training.util.LocationDirectory;

@Service
public class CandidateServiceImpl implements CandidateService {
	private RestTemplate restTemplate;
	@Autowired
	ServletContext application;

	public CandidateServiceImpl(RestTemplateBuilder builder) {
		this.restTemplate = builder.build();
	}

	@Override
	public ResponseObject createCandidate(CandidateForm candidateFormDto, InterviewDto interviewDto,
			EntryTestDto entryTestDto, String dob, String graYear, String appDate, String dateEntry, String dateInter,
			MultipartFile fileCV) {
		if (!dob.isEmpty()) {
			candidateFormDto.setDateOfBirth(LocalDate.parse(dob, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		}
		if (!appDate.isEmpty()) {
			candidateFormDto.setApplicationDate(LocalDate.parse(appDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		}
		if (!graYear.isEmpty()) {
			candidateFormDto
					.setGraduationYear(YearMonth.parse(graYear, DateTimeFormatter.ofPattern("yyyy-MM")).atDay(1));
		}
		if (!dateInter.isEmpty()) {
			interviewDto.setDate(LocalDate.parse(dateInter, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		}
		if (!dateEntry.isEmpty()) {
			entryTestDto.setDate(LocalDate.parse(dateEntry, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		}
		if (!fileCV.isEmpty()) {
			String direcCandidateName = candidateFormDto.getFullName() + candidateFormDto.getDateOfBirth()
					+ candidateFormDto.getEmail() + candidateFormDto.getPhone();
			saveFileToSystem(fileCV, direcCandidateName);
			candidateFormDto.setCv(LocationDirectory.DIR_CV + direcCandidateName + "/" + fileCV.getOriginalFilename());
		}
		RestCreateCandidate restCreateCandidate = new RestCreateCandidate(candidateFormDto, interviewDto, entryTestDto);
		ResponseEntity<ResponseObject> respEntity = restTemplate
				.postForEntity("http://localhost:8100/api/candidate/create", restCreateCandidate, ResponseObject.class);
		return respEntity.getBody();
	}

	public void saveFileToSystem(MultipartFile fileCV, String direcCandidateName) {
		try {
			Path uploadPath = Paths.get(LocationDirectory.DIR_CV + direcCandidateName);
			if (!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}
			Path filePath = uploadPath.resolve(fileCV.getOriginalFilename());
			Files.copy(fileCV.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public CandidateDto getCandidateById(Long id) {
		ResponseEntity<CandidateDto> respEntity = restTemplate
				.getForEntity("http://localhost:8100/api/candidate/get?id=" + id, CandidateDto.class);
		return (CandidateDto) respEntity.getBody();
	}

	@Override
	public CandidateForm getCandidateFormByCandidateId(Long id) {
		ResponseEntity<CandidateForm> respEntity = restTemplate
				.getForEntity("http://localhost:8100/api/candidate/get-form?id=" + id, CandidateForm.class);
		return (CandidateForm) respEntity.getBody();
	}

	@Override
	public ResponseObject updateCandidate(CandidateForm canForm, String graYear, MultipartFile fileCV, Long canId) {
		canForm.setCanId(canId);
		if (!graYear.isEmpty()) {
			canForm.setGraduationYear(YearMonth.parse(graYear, DateTimeFormatter.ofPattern("yyyy-MM")).atDay(1));
		}
		if (!fileCV.isEmpty()) {
			String direcCandidateName = canForm.getFullName() + canForm.getDateOfBirth() + canForm.getEmail()
					+ canForm.getPhone();
			saveFileToSystem(fileCV, direcCandidateName);
			canForm.setCv(LocationDirectory.DIR_CV + direcCandidateName + "/" + fileCV.getOriginalFilename());
		}
		ResponseEntity<ResponseObject> respEntity = restTemplate
				.postForEntity("http://localhost:8100/api/candidate/update", canForm, ResponseObject.class);
		return respEntity.getBody();
	}

	@Override
	public ResponseObject deleteCandidate(Long id) {
		ResponseEntity<ResponseObject> respEntity = restTemplate
				.postForEntity("http://localhost:8100/api/candidate/delete", id, ResponseObject.class);
		return respEntity.getBody();
	}

	@Override
	public ResponseObject transferCandidate(Long id, String transferLocation) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", id.toString());
		params.put("transferLocation", transferLocation);
		ResponseEntity<ResponseObject> respEntity = restTemplate
				.postForEntity("http://localhost:8100/api/candidate/transfer", params, ResponseObject.class);
		return respEntity.getBody();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CandidateListPage> getCandidateList(int pageSize, int pageNumber) {
		ResponseEntity<ResponseObject> respEntity = restTemplate.getForEntity(
				"http://localhost:8100/api/candidate/list?pageSize=" + pageSize + "&pageNumber=" + pageNumber,
				ResponseObject.class);
		return (List<CandidateListPage>) respEntity.getBody().getData();
	}

	@Override
	public int getAllCandidateAmount() {
		ResponseEntity<Integer> respEntity = restTemplate.getForEntity("http://localhost:8100/api/candidate/get-all",
				Integer.class);
		return (Integer) respEntity.getBody();
	}

	@Override
	public ResponseObject deleteManyCandidate(List<Long> canIds) {
		ResponseEntity<ResponseObject> respEntity = restTemplate
				.postForEntity("http://localhost:8100/api/candidate/delete-many", canIds, ResponseObject.class);
		return respEntity.getBody();
	}
}
