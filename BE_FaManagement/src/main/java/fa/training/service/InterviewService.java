package fa.training.service;

import java.util.List;

import fa.training.model.InterviewDto;

public interface InterviewService {
	public List<InterviewDto> getAllInterviewsByCandidateId(Long id);
}
