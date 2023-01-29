package fa.training.service.impl;

import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fa.training.model.ResponseObject;
import fa.training.model.SubSubjectTypeDto;
import fa.training.service.SubSubjectTypeService;

@Service
public class SubSubjectTypeServiceImpl implements SubSubjectTypeService {

	private RestTemplate restTemplate;

	public SubSubjectTypeServiceImpl(RestTemplateBuilder builder) {
		this.restTemplate = builder.build();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SubSubjectTypeDto> getAllSubSubjectType() {
		ResponseEntity<ResponseObject> respEntity = restTemplate.getForEntity("http://localhost:8100/api/sub-subject-type",
				ResponseObject.class);
		return (List<SubSubjectTypeDto>) respEntity.getBody().getData();
	}

}
