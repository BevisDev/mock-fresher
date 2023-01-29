package fa.training.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import fa.training.model.CandidateDto;
import fa.training.model.CandidateForm;
import fa.training.model.CandidateListPage;
import fa.training.model.EntryTestDto;
import fa.training.model.InterviewDto;
import fa.training.model.ResponseObject;

public interface CandidateService {
	public ResponseObject createCandidate(CandidateForm candidateFormDto, InterviewDto interviewDto,
			EntryTestDto entryTestDto, String dob, String graYear, String appDate, String dateEntry, String dateInter,
			MultipartFile fileCV);

	public CandidateDto getCandidateById(Long id);

	public CandidateForm getCandidateFormByCandidateId(Long id);

	public ResponseObject updateCandidate(CandidateForm canForm, String graYear, MultipartFile fileCV, Long canId);

	public ResponseObject deleteCandidate(Long id);

	public ResponseObject transferCandidate(Long id, String transferLocation);

	public List<CandidateListPage> getCandidateList(int pageSize, int pageNumber);

	public int getAllCandidateAmount();

	public ResponseObject deleteManyCandidate(List<Long> canIds);
}
