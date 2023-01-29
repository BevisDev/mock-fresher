package fa.training.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fa.training.model.ResponseObject;
import fa.training.model.SubSubjectTypeDto;
import fa.training.service.SubSubjectTypeService;

@Service
public class SubSubjectTypeServiceImpl implements SubSubjectTypeService {
	@Autowired
	private RestTemplate restTemplate;

	@SuppressWarnings("unchecked")
	@Override
	public List<SubSubjectTypeDto> getAllSubSubjectType() {
		ResponseEntity<ResponseObject> respEntity = restTemplate.getForEntity("http://localhost:8100/api/sub-subject-type",
				ResponseObject.class);
		return (List<SubSubjectTypeDto>) respEntity.getBody().getData();
	}

}
