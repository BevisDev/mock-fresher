package fa.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fa.training.model.ChannelDto;
import fa.training.model.ResponseObject;
import fa.training.service.ChannelService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/api/channel")
@Slf4j
@CrossOrigin
public class ChannelController {
	@Autowired
	private ChannelService channelService;

	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_DELIVERYMANAGER','ROLE_SYSTEM','ROLE_CLASSADMIN')")
	public ResponseEntity<?> getAllUniversities() {
		ResponseEntity<Object> responseEntity = ResponseEntity.badRequest().body("Get all channels is failed");
		try {
			List<ChannelDto> channelDtos = channelService.getAllChannels();
			responseEntity = ResponseEntity.ok().body(
					new ResponseObject(String.valueOf(HttpStatus.OK), "Get all channels is succeed", channelDtos));
		} catch (Exception e) {
			log.error("Get all channels is failed ");
		}
		return responseEntity;
	}

	@GetMapping("/get-channel")
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_DELIVERYMANAGER','ROLE_SYSTEM', 'ROLE_REC', 'ROLE_CLASSADMIN')")
	public ResponseEntity<?> getChannelById(@RequestParam("id") Long id) {
		ResponseEntity<Object> responseEntity = ResponseEntity.badRequest().body("Get channel by id is failed");
		try {
			ChannelDto channelDto = channelService.getChannelById(id);
			responseEntity = ResponseEntity.ok().body(channelDto);
		} catch (Exception e) {
			log.error("Get channel by id is failed ");
		}
		return responseEntity;
	}
}
