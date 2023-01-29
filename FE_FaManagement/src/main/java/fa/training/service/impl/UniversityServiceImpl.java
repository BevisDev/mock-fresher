package fa.training.service.impl;

import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fa.training.model.ResponseObject;
import fa.training.model.UniversityDto;
import fa.training.service.UniversityService;

@Service
public class UniversityServiceImpl implements UniversityService {
	private RestTemplate restTemplate;

	public UniversityServiceImpl(RestTemplateBuilder builder) {
		this.restTemplate = builder.build();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UniversityDto> getAllUniversities() {
		ResponseEntity<ResponseObject> respEntity = restTemplate.getForEntity("http://localhost:8100/api/university",
				ResponseObject.class);
		return (List<UniversityDto>) respEntity.getBody().getData();
	}

	@Override
	public UniversityDto getUniversityById(Long id) {
		ResponseEntity<UniversityDto> respEntity = restTemplate
				.getForEntity("http://localhost:8100/api/university/get-university?id=" + id, UniversityDto.class);
		return (UniversityDto) respEntity.getBody();
	}
}
