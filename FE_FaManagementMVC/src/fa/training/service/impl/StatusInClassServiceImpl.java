package fa.training.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fa.training.model.ResponseObject;
import fa.training.model.StatusInClassDto;
import fa.training.service.StatusInClassService;

@Service
public class StatusInClassServiceImpl implements StatusInClassService{
	@Autowired
	private RestTemplate restTemplate;
	@SuppressWarnings("unchecked")
	@Override
	public List<StatusInClassDto> getAllStatus() {
		ResponseEntity<ResponseObject> respEntity = restTemplate.getForEntity("http://localhost:8100/api/status-in-class", ResponseObject.class);
		return (List<StatusInClassDto>) respEntity.getBody().getData();
	}

}
