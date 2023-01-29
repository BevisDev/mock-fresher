package fa.training.service.impl;

import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fa.training.model.ChannelDto;
import fa.training.model.ResponseObject;
import fa.training.service.ChannelService;

@Service
public class ChannelServiceImpl implements ChannelService {

	private RestTemplate restTemplate;

	public ChannelServiceImpl(RestTemplateBuilder builder) {
		this.restTemplate = builder.build();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ChannelDto> getAllChannels() {
		ResponseEntity<ResponseObject> respEntity = restTemplate.getForEntity("http://localhost:8100/api/channel",
				ResponseObject.class);
		return (List<ChannelDto>) respEntity.getBody().getData();
	}

	@Override
	public ChannelDto getChannelById(Long id) {
		ResponseEntity<ChannelDto> respEntity = restTemplate
				.getForEntity("http://localhost:8100/api/channel/get-channel?id=" + id, ChannelDto.class);
		return (ChannelDto) respEntity.getBody();
	}

}
