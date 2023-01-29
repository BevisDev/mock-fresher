package fa.training.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fa.training.model.DeliveryTypeDto;
import fa.training.model.ResponseObject;
import fa.training.service.DeliveryTypeService;

@Service
public class DeliveryTypeServiceImpl implements DeliveryTypeService{
	@Autowired
	private RestTemplate restTemplate;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DeliveryTypeDto> getAllDeliveryType() {
		ResponseEntity<ResponseObject> respEntity = restTemplate.getForEntity("http://localhost:8100/api/delivery-type", ResponseObject.class);
		return (List<DeliveryTypeDto>) respEntity.getBody().getData();
	}

}
