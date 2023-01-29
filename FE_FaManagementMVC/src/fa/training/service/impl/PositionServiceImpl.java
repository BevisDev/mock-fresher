package fa.training.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fa.training.model.PositionDto;
import fa.training.model.ResponseObject;
import fa.training.service.PositionService;

@Service
public class PositionServiceImpl implements PositionService {
	@Autowired
	private RestTemplate restTemplate;

	@SuppressWarnings("unchecked")
	@Override
	public List<PositionDto> getAllPosition() {
		ResponseEntity<ResponseObject> respEntity = restTemplate.getForEntity("http://localhost:8100/api/position", ResponseObject.class);
		return (List<PositionDto>) respEntity.getBody().getData();
	}
}
