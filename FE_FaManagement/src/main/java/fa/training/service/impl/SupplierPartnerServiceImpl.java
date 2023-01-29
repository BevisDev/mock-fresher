package fa.training.service.impl;

import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fa.training.model.ResponseObject;
import fa.training.model.SupplierPartnerDto;
import fa.training.service.SupplierPartnerService;

@Service
public class SupplierPartnerServiceImpl implements SupplierPartnerService{

	private RestTemplate restTemplate;

	public SupplierPartnerServiceImpl(RestTemplateBuilder builder) {
		this.restTemplate = builder.build();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SupplierPartnerDto> getAllSupplierPartner() {
		ResponseEntity<ResponseObject> respEntity = restTemplate.getForEntity("http://localhost:8100/api/supplier-partner",
				ResponseObject.class);
		return (List<SupplierPartnerDto>) respEntity.getBody().getData();
	}

	
}
