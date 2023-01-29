package fa.training.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import fa.training.entity.FormatType;
import fa.training.model.FormatTypeDto;

@Mapper
public interface FormatTypeMapper {
	FormatTypeMapper INSTANCE = Mappers.getMapper(FormatTypeMapper.class);

	FormatTypeDto entityToDto(FormatType formatType);

	FormatType dtoToEntity(FormatTypeDto formatTypeDto);

	List<FormatTypeDto> listEntityToListDto(List<FormatType> listOfFormatType);
}
