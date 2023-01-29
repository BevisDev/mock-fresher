package fa.training.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import fa.training.entity.ClassBatchType;
import fa.training.model.ClassBatchTypeDto;

@Mapper
public interface ClassBatchTypeMapper {
	ClassBatchTypeMapper INSTANCE = Mappers.getMapper(ClassBatchTypeMapper.class);

	ClassBatchTypeDto entityToDto(ClassBatchType ClassBatchType);

	ClassBatchType dtoToEntity(ClassBatchTypeDto ClassBatchTypeDto);

	List<ClassBatchTypeDto> listEntityToListDto(List<ClassBatchType> listOfClassBatchType);
}
