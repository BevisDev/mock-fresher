package fa.training.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import fa.training.entity.SubSubjectType;
import fa.training.model.SubSubjectTypeDto;

@Mapper
public interface SubSubjectTypeMapper {
	SubSubjectTypeMapper INSTANCE = Mappers.getMapper(SubSubjectTypeMapper.class);

	SubSubjectTypeDto entityToDto(SubSubjectType subSubjectType);

	SubSubjectType dtoToEntity(SubSubjectTypeDto subSubjectTypeDto);

	List<SubSubjectTypeDto> listEntityToListDto(List<SubSubjectType> listOfSubSubjectType);
}
