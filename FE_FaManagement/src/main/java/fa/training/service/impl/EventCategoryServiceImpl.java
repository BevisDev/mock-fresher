package fa.training.service.impl;

import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fa.training.model.EventCategoryDto;
import fa.training.model.ResponseObject;
import fa.training.service.EventCategoryService;

@Service
public class EventCategoryServiceImpl implements EventCategoryService{

	private RestTemplate restTemplate;

	public EventCategoryServiceImpl(RestTemplateBuilder builder) {
		this.restTemplate = builder.build();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EventCategoryDto> getAllEventCategory() {
		ResponseEntity<ResponseObject> respEntity = restTemplate.getForEntity("http://localhost:8100/api/event-category", ResponseObject.class);
		return (List<EventCategoryDto>) respEntity.getBody().getData();
	}

}
