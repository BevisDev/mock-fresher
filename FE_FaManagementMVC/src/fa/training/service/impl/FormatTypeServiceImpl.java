package fa.training.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fa.training.model.FormatTypeDto;
import fa.training.model.ResponseObject;
import fa.training.service.FormatTypeService;

@Service
public class FormatTypeServiceImpl implements FormatTypeService {
	@Autowired
	private RestTemplate restTemplate;

	@SuppressWarnings("unchecked")
	@Override
	public List<FormatTypeDto> getAllFormatType() {
		ResponseEntity<ResponseObject> respEntity = restTemplate.getForEntity("http://localhost:8100/api/format-type",
				ResponseObject.class);
		return (List<FormatTypeDto>) respEntity.getBody().getData();
	}
}
