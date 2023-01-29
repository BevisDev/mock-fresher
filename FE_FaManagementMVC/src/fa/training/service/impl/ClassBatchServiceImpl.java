package fa.training.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fa.training.model.AuditDto;
import fa.training.model.BudgetDetailDto;
import fa.training.model.ClassBatchDetailDto;
import fa.training.model.ClassBatchDto;
import fa.training.model.ClassRequestInfoDto;
import fa.training.model.ResponseObject;
import fa.training.model.TraineeClassDto;
import fa.training.service.ClassBatchService;
import fa.training.util.PaginationResult;
import fa.training.util.ResponseObjectPaginated;
import fa.training.util.SearchRequest;

@Service
public class ClassBatchServiceImpl implements ClassBatchService {
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public PaginationResult paginatedClassResult(int pageSize, int pageNum, ClassBatchDto searchData) {

		if (searchData == null) {
			searchData = new ClassBatchDto();
			searchData.setClassId((long) (-1));
			searchData.setLocationID((long) 1);
			searchData.setStatus("All");
			searchData.setActualStartDate(null);
			searchData.setActualEndDate(null);
		}

		SearchRequest jsonObj = new SearchRequest();
		List<String> listColumnName = new ArrayList<>();
		listColumnName.add("classId");
		listColumnName.add("className");
		listColumnName.add("classCode");
		listColumnName.add("locationID");
		listColumnName.add("detailLocation");
		listColumnName.add("actualStartDate");
		listColumnName.add("actualEndDate");
		listColumnName.add("status");
		jsonObj.setColumns(listColumnName);
		jsonObj.setSearchData(searchData);
		ResponseEntity<ResponseObjectPaginated> respEntity = restTemplate.exchange(
				"http://localhost:8100/api/classBatch/search?pageNum=" + pageNum + "&pageSize=" + pageSize,
				HttpMethod.PUT, new HttpEntity<SearchRequest>(jsonObj), ResponseObjectPaginated.class);
		return (PaginationResult) respEntity.getBody().getData();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassBatchDto> getAllClassBatch() {
		ResponseEntity<ResponseObject> respEntity = restTemplate.getForEntity("http://localhost:8100/api/classBatch",
				ResponseObject.class);
		return (List<ClassBatchDto>) respEntity.getBody().getData();
	}

	public String addClass(ClassBatchDetailDto batchDetailDto) {
		removeDefaultBudget(batchDetailDto);
		removeDefaultAudit(batchDetailDto);
		ResponseEntity<ResponseObject> respEntity = restTemplate
				.postForEntity("http://localhost:8100/api/classBatch/add", batchDetailDto, ResponseObject.class);
		return respEntity.getBody().getMessage();
	}

	@Override
	public ClassBatchDetailDto getDetailClassById(Long id) {
		ResponseEntity<ClassBatchDetailDto> entity = restTemplate
				.getForEntity("http://localhost:8100/api/classBatch/view/" + id, ClassBatchDetailDto.class);
		return entity.getBody();

	}

	@Override
	public String requestMoreInfo(ClassRequestInfoDto classRequestInfoDto) {
		ResponseEntity<ResponseObject> respEntity = restTemplate.postForEntity(
				"http://localhost:8100/api/classBatch/request-info", classRequestInfoDto, ResponseObject.class);
		return respEntity.getBody().getMessage();
	}

	@Override
	public String updateDetailClassById(Long classId, ClassBatchDetailDto batchDetailDto) {
		removeDefaultBudget(batchDetailDto);
		removeDefaultAudit(batchDetailDto);
		HttpEntity<ClassBatchDetailDto> request = new HttpEntity<>(batchDetailDto);
		ResponseEntity<ResponseObject> respEntity = restTemplate.exchange(
				"http://localhost:8100/api/classBatch/edit/" + classId, HttpMethod.PUT, request, ResponseObject.class);
		return respEntity.getBody().getMessage();
	}

	@Override
	public String cancelClassesByIds(List<Long> classIds) {
		HttpEntity<List<Long>> request = new HttpEntity<>(classIds);
		ResponseEntity<ResponseObject> respEntity = restTemplate.exchange("http://localhost:8100/api/classBatch/cancel",
				HttpMethod.PUT, request, ResponseObject.class);
		return respEntity.getBody().getMessage();
	}

	
	@Override
	public String submitClassById(Long classId) {
		//HttpEntity<Long> request = new HttpEntity<>(classId);
		ResponseEntity<ResponseObject> respEntity = restTemplate.exchange("http://localhost:8100/api/classBatch/submit?classId="+classId,
				HttpMethod.PUT, null , ResponseObject.class);
		return respEntity.getBody().getMessage();
	}

	@Override
	public String removeTraineeFromClass(TraineeClassDto traineeClassDto) {
		ResponseEntity<ResponseObject> respEntity = restTemplate.postForEntity(
				"http://localhost:8100/api/classBatch/remove-trainee", traineeClassDto, ResponseObject.class);
		return respEntity.getBody().getMessage();
	}

	private void removeDefaultBudget(ClassBatchDetailDto batchDetailDto) {
		if (batchDetailDto.getBudgetDetailDtos() == null)
			batchDetailDto.setBudgetDetailDtos(new ArrayList<BudgetDetailDto>());
		if (batchDetailDto.getBudgetDetailDtos().size() == 1) {
			BudgetDetailDto budgetDetailDto = batchDetailDto.getBudgetDetailDtos().get(0);
			if (budgetDetailDto.getItem().equals("") || budgetDetailDto.getUnit().equals("")
					|| budgetDetailDto.getUnitExpense() == null || budgetDetailDto.getQuantity() == null) {
				batchDetailDto.getBudgetDetailDtos().remove(budgetDetailDto);
			}
		}
	}

	private void removeDefaultAudit(ClassBatchDetailDto batchDetailDto) {
		if (batchDetailDto.getAuditDtos() == null)
			batchDetailDto.setAuditDtos(new ArrayList<AuditDto>());
		if (batchDetailDto.getAuditDtos().size() == 1) {
			AuditDto auditDto = batchDetailDto.getAuditDtos().get(0);
			if (auditDto.getDate() == null || auditDto.getAction().equals("") || auditDto.getPic().equals("")
					|| auditDto.getDeadline() == null) {
				batchDetailDto.getAuditDtos().remove(auditDto);
			}
		}
	}

	@Override
	public String finishClassById(List<Long> classIds) {
		HttpEntity<List<Long>> request = new HttpEntity<>(classIds);
		ResponseEntity<ResponseObject> respEntity = restTemplate.exchange("http://localhost:8100/api/classBatch/finish",
				HttpMethod.PUT, request, ResponseObject.class);
		return respEntity.getBody().getMessage();
	}
	
	@Override
	public ResponseObject acceptClass(Long classId, String newStatus) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("classId", classId.toString());
		params.put("newStatus", newStatus);
		ResponseEntity<ResponseObject> respEntity = restTemplate
				.postForEntity("http://localhost:8100/api/classBatch/accept", params, ResponseObject.class);
		return respEntity.getBody();
	}

	@Override
	public ResponseObject declineClass(Long classId, String newStatus, String remarks) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("classId", classId.toString());
		params.put("newStatus", newStatus);
		params.put("remarks", remarks);
		ResponseEntity<ResponseObject> respEntity = restTemplate
				.postForEntity("http://localhost:8100/api/classBatch/decline", params, ResponseObject.class);
		return respEntity.getBody();
	}

	@Override
	public ResponseObject startClass(Long classId, String newStatus) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("classId", classId.toString());
		params.put("newStatus", newStatus);
		ResponseEntity<ResponseObject> respEntity = restTemplate
				.postForEntity("http://localhost:8100/api/classBatch/start", params, ResponseObject.class);
		return respEntity.getBody();
	}

	@Override
	public String closeClassById(List<Long> classIds) {
		HttpEntity<List<Long>> request = new HttpEntity<>(classIds);
		ResponseEntity<ResponseObject> responseEntity = restTemplate.exchange("http://localhost:8100/api/classBatch/close",
				HttpMethod.PUT,request,ResponseObject.class);
		return responseEntity.getBody().getMessage(); 
	}



}
