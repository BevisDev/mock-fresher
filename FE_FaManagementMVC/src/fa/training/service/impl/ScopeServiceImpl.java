package fa.training.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fa.training.model.ResponseObject;
import fa.training.model.ScopeDto;
import fa.training.service.ScopeService;

@Service
public class ScopeServiceImpl implements ScopeService{
	@Autowired
	private RestTemplate restTemplate;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ScopeDto> getAllScope() {
		ResponseEntity<ResponseObject> respEntity = restTemplate.getForEntity("http://localhost:8100/api/scope", ResponseObject.class);
		return (List<ScopeDto>) respEntity.getBody().getData();
	}

}
