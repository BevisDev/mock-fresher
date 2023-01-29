package fa.training.service;

import java.util.List;

import fa.training.model.ChannelDto;

public interface ChannelService {
	public List<ChannelDto> getAllChannels();

	public ChannelDto getChannelById(Long id);
}
