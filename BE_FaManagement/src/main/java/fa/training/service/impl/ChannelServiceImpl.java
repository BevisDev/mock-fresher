package fa.training.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fa.training.entity.Channel;
import fa.training.mapper.ChannelMapper;
import fa.training.model.ChannelDto;
import fa.training.repository.ChannelRepository;
import fa.training.service.ChannelService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class ChannelServiceImpl implements ChannelService {
	@Autowired
	private ChannelRepository channelRepository;

	@Override
	public List<ChannelDto> getAllChannels() {
		return ChannelMapper.INSTANCE.listEntityToListDto(channelRepository.findByIsDelete(false));
	}

	@Override
	public boolean addChannel(ChannelDto channelDto) {
		try {
			Channel channel = ChannelMapper.INSTANCE.dtoToEntity(channelDto);
			channel.setDelete(false);
			channelRepository.save(channel);
			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
	}

	@Override
	public ChannelDto getChannelById(Long id) {
		Channel channel = channelRepository.getById(id);
		return ChannelMapper.INSTANCE.entityToDto(channel);
	}
}
