package fa.training.service.impl;

import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fa.training.model.FacultyDto;
import fa.training.model.ResponseObject;
import fa.training.service.FacultyService;

@Service
public class FacultyServiceImpl implements FacultyService {
	private RestTemplate restTemplate;

	public FacultyServiceImpl(RestTemplateBuilder builder) {
		this.restTemplate = builder.build();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FacultyDto> getAllFaculties() {
		ResponseEntity<ResponseObject> respEntity = restTemplate.getForEntity("http://localhost:8100/api/faculty",
				ResponseObject.class);
		return (List<FacultyDto>) respEntity.getBody().getData();
	}

	@Override
	public FacultyDto getFacultyById(Long id) {
		ResponseEntity<FacultyDto> respEntity = restTemplate
				.getForEntity("http://localhost:8100/api/faculty/get-faculty?id=" + id, FacultyDto.class);
		return (FacultyDto) respEntity.getBody();
	}

}
