package fa.training.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fa.training.model.BudgetDto;
import fa.training.model.ResponseObject;
import fa.training.service.BudgetService;

@Service
public class BudgetServiceImpl implements BudgetService {
	@Autowired
	private RestTemplate restTemplate;


	@SuppressWarnings("unchecked")
	@Override
	public List<BudgetDto> getAllBudget() {
		ResponseEntity<ResponseObject> respEntity = restTemplate.getForEntity("http://localhost:8100/api/budget",
				ResponseObject.class);
		return (List<BudgetDto>) respEntity.getBody().getData();
	}

}
