package fa.training.service.impl;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fa.training.model.ResponseObject;
import fa.training.service.LoggedService;

@Service
public class LoggedServiceImpl implements LoggedService {
	private RestTemplate restTemplate;

	public LoggedServiceImpl(RestTemplateBuilder builder) {
		this.restTemplate = builder.build();
	}

	@Override
	public boolean isLogged() {
		try {
			ResponseEntity<ResponseObject> respEntity = restTemplate.getForEntity("http://localhost:8100/api/logged",
					ResponseObject.class);
			return (boolean) respEntity.getBody().getData();
		} catch (Exception e) {
			return false;
		}
	}
}
