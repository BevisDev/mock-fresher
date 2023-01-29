package fa.training.service.impl;

import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fa.training.model.ClassBatchTypeDto;
import fa.training.model.ResponseObject;
import fa.training.service.ClassBatchTypeService;

@Service
public class ClassBatchTypeServiceImpl implements ClassBatchTypeService{

	private RestTemplate restTemplate;

	public ClassBatchTypeServiceImpl(RestTemplateBuilder builder) {
		this.restTemplate = builder.build();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ClassBatchTypeDto> getAllClassBatchType() {
		ResponseEntity<ResponseObject> respEntity = restTemplate.getForEntity("http://localhost:8100/api/class-batch-type", ResponseObject.class);
		return (List<ClassBatchTypeDto>) respEntity.getBody().getData();
	}

	
}
