package fa.training.service.impl;

import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fa.training.model.FormatTypeDto;
import fa.training.model.ResponseObject;
import fa.training.service.FormatTypeService;

@Service
public class FormatTypeServiceImpl implements FormatTypeService {

	private RestTemplate restTemplate;

	public FormatTypeServiceImpl(RestTemplateBuilder builder) {
		this.restTemplate = builder.build();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FormatTypeDto> getAllFormatType() {
		ResponseEntity<ResponseObject> respEntity = restTemplate.getForEntity("http://localhost:8100/api/format-type",
				ResponseObject.class);
		return (List<FormatTypeDto>) respEntity.getBody().getData();
	}
}
