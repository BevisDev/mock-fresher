package fa.training.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import fa.training.entity.Channel;
import fa.training.model.ChannelDto;

@Mapper
public interface ChannelMapper {
	ChannelMapper INSTANCE = Mappers.getMapper(ChannelMapper.class);

	ChannelDto entityToDto(Channel channel);

	Channel dtoToEntity(ChannelDto channelDto);

	List<ChannelDto> listEntityToListDto(List<Channel> channels);
}
