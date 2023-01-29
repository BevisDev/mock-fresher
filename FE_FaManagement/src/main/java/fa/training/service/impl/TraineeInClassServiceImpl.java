package fa.training.service.impl;

import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fa.training.model.ResponseObject;
import fa.training.model.TraineeUpdateStatusDto;
import fa.training.service.TraineeInClassService;

@Service
public class TraineeInClassServiceImpl implements TraineeInClassService{

	private RestTemplate restTemplate;

	public TraineeInClassServiceImpl(RestTemplateBuilder builder) {
		this.restTemplate = builder.build();
	}

	@Override
	public String updateStatusInClass(List<TraineeUpdateStatusDto> traineeUpdateStatusDtos) {
		HttpEntity<List<TraineeUpdateStatusDto>> request = new HttpEntity<>(traineeUpdateStatusDtos);

		ResponseEntity<ResponseObject> respObject = restTemplate.exchange("http://localhost:8100/api/trainee/update-status", HttpMethod.PUT, request, ResponseObject.class);
		return respObject.getBody().getMessage();
	}


}
