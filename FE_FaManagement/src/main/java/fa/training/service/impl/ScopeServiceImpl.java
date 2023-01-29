package fa.training.service.impl;

import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fa.training.model.ResponseObject;
import fa.training.model.ScopeDto;
import fa.training.service.ScopeService;

@Service
public class ScopeServiceImpl implements ScopeService{

	private RestTemplate restTemplate;

	public ScopeServiceImpl(RestTemplateBuilder builder) {
		this.restTemplate = builder.build();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ScopeDto> getAllScope() {
		ResponseEntity<ResponseObject> respEntity = restTemplate.getForEntity("http://localhost:8100/api/scope", ResponseObject.class);
		return (List<ScopeDto>) respEntity.getBody().getData();
	}

}
