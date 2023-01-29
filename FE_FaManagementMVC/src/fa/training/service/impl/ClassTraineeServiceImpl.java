package fa.training.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import fa.training.model.PageTraineeProfileDto;
import fa.training.model.ResponseObject;
import fa.training.model.TraineeClassDto;
import fa.training.service.ClassTraineeService;

@Service
public class ClassTraineeServiceImpl implements ClassTraineeService {
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public PageTraineeProfileDto getTraineeNotInClass(Long classId, Integer offset, Integer pageSize) {
		ResponseEntity<PageTraineeProfileDto> responseEntity = restTemplate.exchange(
				"http://localhost:8100/api/class/" + classId + "/trainee", HttpMethod.GET, null,
				PageTraineeProfileDto.class);
		return responseEntity.getBody();
	}

	@Override
	public String addTraineeToClass(TraineeClassDto traineeClassDto) {
		ResponseEntity<ResponseObject> responseEntity = restTemplate.postForEntity(
				"http://localhost:8100/api/class/" + traineeClassDto.getClassId() + "/trainee/add", traineeClassDto,
				ResponseObject.class);
		return responseEntity.getBody().getMessage();
	}

	@Override
	public String importTraineeToClass(MultipartFile file, Long classID) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		String message = "Failed to import trainee";
		Path tempFile;
		try {
			tempFile = Files.createTempFile(null, null);
			Files.write(tempFile, file.getBytes());
			File fileToSend = tempFile.toFile();
			MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
			parts.add("file", new FileSystemResource(fileToSend));
			HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(parts,
					headers);
			ResponseEntity<ResponseObject> responseEntity = restTemplate.postForEntity(
					"http://localhost:8100/api/class/" + classID + "/trainee/import", httpEntity, ResponseObject.class);
			message = responseEntity.getBody().getMessage();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}

}
