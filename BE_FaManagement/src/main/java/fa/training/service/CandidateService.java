package fa.training.service;

import java.util.List;

import fa.training.model.CandidateDto;
import fa.training.model.CandidateForm;
import fa.training.model.CandidateListPage;
import fa.training.model.RestCreateCandidate;

public interface CandidateService {
	public boolean createCandidate(RestCreateCandidate restCreateCandidate);

	public CandidateDto getCandidateById(Long id);

	public CandidateForm getCandidateFormByCandidateId(Long id);

	public boolean updateCandidate(CandidateForm candidateForm);

	public boolean deleteCandidate(Long id);

	public boolean transferCandidate(Long id, String transferLocation);

	public List<CandidateListPage> getCandidateList(int pageSize, int pageNumber);
	
	public int getAllCandidateAmount();
	
	public boolean deleteManyCandidate(List<Long> canIds);
}
