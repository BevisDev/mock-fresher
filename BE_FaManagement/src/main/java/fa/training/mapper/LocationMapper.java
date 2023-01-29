package fa.training.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import fa.training.entity.Location;
import fa.training.model.LocationDto;

@Mapper
public interface LocationMapper {
	LocationMapper INSTANCE = Mappers.getMapper( LocationMapper.class );
	
	LocationDto entityToDto(Location location);
	Location dtoToEntity(LocationDto locationDto);
	List<LocationDto> listEntityToListDto(List<Location> listOfLocaiton);
}
