package fa.training.service.impl;

import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fa.training.model.ClassAdminDto;
import fa.training.model.ResponseObject;
import fa.training.service.ClassAdminService;

@Service
public class ClassAdminServiceImpl implements ClassAdminService{

	private RestTemplate restTemplate;

	public ClassAdminServiceImpl(RestTemplateBuilder builder) {
		this.restTemplate = builder.build();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ClassAdminDto> getAllClassAdmin() {
		ResponseEntity<ResponseObject> respEntity = restTemplate.getForEntity("http://localhost:8100/api/class-admin", ResponseObject.class);
		return (List<ClassAdminDto>) respEntity.getBody().getData();
	}

}
