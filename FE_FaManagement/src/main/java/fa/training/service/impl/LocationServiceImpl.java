package fa.training.service.impl;

import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fa.training.model.LocationDto;
import fa.training.model.ResponseObject;
import fa.training.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService {

	private RestTemplate restTemplate;

	public LocationServiceImpl(RestTemplateBuilder builder) {
		this.restTemplate = builder.build();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocationDto> getAllLocation() {
		ResponseEntity<ResponseObject> respEntity = restTemplate.getForEntity("http://localhost:8100/api/location",
				ResponseObject.class);
		return (List<LocationDto>) respEntity.getBody().getData();
	}

	@Override
	public LocationDto getLocationById(Long id) {
		ResponseEntity<LocationDto> respEntity = restTemplate
				.getForEntity("http://localhost:8100/api/location/get-location?id=" + id, LocationDto.class);
		return (LocationDto) respEntity.getBody();
	}

}
