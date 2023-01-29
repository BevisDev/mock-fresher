package fa.training.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.mapper.InterviewMapper;
import fa.training.model.InterviewDto;
import fa.training.repository.InterviewRepository;
import fa.training.service.InterviewService;

@Service
public class InterviewServiceImpl implements InterviewService {
	@Autowired
	private InterviewRepository interviewRepository;

	@Override
	public List<InterviewDto> getAllInterviewsByCandidateId(Long id) {
		return InterviewMapper.INSTANCE
				.listEntityToListDto(interviewRepository.findByIsDeleteAndCandidateId(false, id));
	}

}
