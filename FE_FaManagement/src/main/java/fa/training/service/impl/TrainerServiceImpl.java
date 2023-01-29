package fa.training.service.impl;

import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fa.training.model.ResponseObject;
import fa.training.model.TrainerDto;
import fa.training.service.TrainerService;

@Service
public class TrainerServiceImpl implements TrainerService {

	private RestTemplate restTemplate;

	public TrainerServiceImpl(RestTemplateBuilder builder) {
		this.restTemplate = builder.build();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TrainerDto> getAllTrainer() {
		ResponseEntity<ResponseObject> respEntity = restTemplate.getForEntity("http://localhost:8100/api/trainer",
				ResponseObject.class);
		return (List<TrainerDto>) respEntity.getBody().getData();
	}

}
