package fa.training.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import fa.training.entity.Position;
import fa.training.model.PositionDto;

@Mapper
public interface PositionMapper {
	PositionMapper INSTANCE = Mappers.getMapper(PositionMapper.class);

	PositionDto entityToDto(Position position);

	Position dtoToEntity(PositionDto positionDto);

	List<PositionDto> listEntityToListDto(List<Position> listOfLocaiton);
}
