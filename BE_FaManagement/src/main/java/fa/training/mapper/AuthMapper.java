package fa.training.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import fa.training.entity.User;
import fa.training.model.AuthDto;

@Mapper
public interface AuthMapper {
	
	AuthMapper INSTANCE = Mappers.getMapper( AuthMapper.class );
	
	AuthDto userToAuthDto(User user);
	User authDtoToUser(AuthDto authDto);
}