package fa.training.service.impl;

import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fa.training.model.EntryTestDto;
import fa.training.model.ResponseObject;
import fa.training.service.EntryTestService;

@Service
public class EntryTestServiceImpl implements EntryTestService {
	private RestTemplate restTemplate;

	public EntryTestServiceImpl(RestTemplateBuilder builder) {
		this.restTemplate = builder.build();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EntryTestDto> getAllEntryTestDto(Long candidateId) {
		ResponseEntity<ResponseObject> respEntity = restTemplate.getForEntity(
				"http://localhost:8100/api/entry-test/get-list-by-candidate-id?id=" + candidateId,
				ResponseObject.class);
		return (List<EntryTestDto>) respEntity.getBody().getData();
	}

}
