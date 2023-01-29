package fa.training.service.impl;

import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fa.training.model.ResponseObject;
import fa.training.model.SubjectTypeDto;
import fa.training.service.SubjectTypeService;

@Service
public class SubjectTypeServiceImpl implements SubjectTypeService{

	private RestTemplate restTemplate;

	public SubjectTypeServiceImpl(RestTemplateBuilder builder) {
		this.restTemplate = builder.build();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SubjectTypeDto> getAllSubjectType() {
		ResponseEntity<ResponseObject> respEntity = restTemplate.getForEntity("http://localhost:8100/api/subject-type", ResponseObject.class);
		return (List<SubjectTypeDto>) respEntity.getBody().getData();
	}

}
