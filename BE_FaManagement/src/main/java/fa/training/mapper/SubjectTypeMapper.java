package fa.training.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import fa.training.entity.SubjectType;
import fa.training.model.SubjectTypeDto;

@Mapper
public interface SubjectTypeMapper {
	SubjectTypeMapper INSTANCE = Mappers.getMapper(SubjectTypeMapper.class);

	SubjectTypeDto entityToDto(SubjectType subjectType);

	SubjectType dtoToEntity(SubjectTypeDto subjectTypeDto);

	List<SubjectTypeDto> listEntityToListDto(List<SubjectType> listOfSubjectType);
}
