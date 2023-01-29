package fa.training.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fa.training.model.PageTraineeProfileDto;
import fa.training.model.ResponseObject;
import fa.training.model.TraineeProfileDto;
import fa.training.service.TraineeProfileService;

@Service
public class TraineeProfileServiceImpl implements TraineeProfileService {
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public TraineeProfileDto getTraineeProfileById(Long id) {
		ResponseEntity<TraineeProfileDto> respEntity = restTemplate.getForEntity(
				"http://localhost:8100/api/trainee-profile/get-trainee-profile?id=" + id, TraineeProfileDto.class);
		return (TraineeProfileDto) respEntity.getBody();
	}

	@Override
	public PageTraineeProfileDto getTraineeProfileByClassId(Long classId, Integer offSet, Integer pageSize) {
		ResponseEntity<PageTraineeProfileDto> respEntity = restTemplate.exchange("http://localhost:8100/api/classBatch/view/"
				+ classId + "/trainee?offset=" + offSet + "&pageSize=" + pageSize, HttpMethod.GET, null,
				PageTraineeProfileDto.class);
		return respEntity.getBody();
	}

	@Override
	public PageTraineeProfileDto getTraineeList(Integer offset, Integer pageSize) {
		ResponseEntity<PageTraineeProfileDto> responseEntity = restTemplate.exchange("http://localhost:8100/api/trainee-profile/get-all"
				+ "?offset="+offset+"&pageSize="+pageSize, HttpMethod.GET,null,PageTraineeProfileDto.class);
		return responseEntity.getBody();
	}

	@Override
	public TraineeProfileDto getTraineeProfileByAccount(String account) {
		ResponseEntity<TraineeProfileDto> respEntity = restTemplate.getForEntity(
				"http://localhost:8100/api/trainee-profile/get-trainee-infor?account=" + account, TraineeProfileDto.class);
		return respEntity.getBody();
	}

	@Override
	public String deleteTraineeProfileById(List<Long> traineeIds) {
		HttpEntity<List<Long>> request = new HttpEntity<List<Long>>(traineeIds);
		ResponseEntity<ResponseObject> responseEntity = restTemplate.exchange("http://localhost:8100/api/trainee-profile/delete-trainee",
				HttpMethod.POST,request,ResponseObject.class);
		return responseEntity.getBody().getMessage();
	}

}
