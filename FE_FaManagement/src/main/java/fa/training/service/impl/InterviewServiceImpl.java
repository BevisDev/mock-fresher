package fa.training.service.impl;

import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fa.training.model.InterviewDto;
import fa.training.model.ResponseObject;
import fa.training.service.InterviewService;

@Service
public class InterviewServiceImpl implements InterviewService {
	private RestTemplate restTemplate;

	public InterviewServiceImpl(RestTemplateBuilder builder) {
		this.restTemplate = builder.build();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InterviewDto> getAllInterviewsByCandidateId(Long candidateId) {
		ResponseEntity<ResponseObject> respEntity = restTemplate.getForEntity(
				"http://localhost:8100/api/interview/get-list-by-candidate-id?id=" + candidateId,
				ResponseObject.class);
		return (List<InterviewDto>) respEntity.getBody().getData();
	}

}
