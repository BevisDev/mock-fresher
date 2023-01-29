package fa.training.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import fa.training.entity.ClassBatch;
import fa.training.model.ClassBatchDto;

@Mapper
public interface ClassBatchMapper {

	ClassBatchMapper INSTANCE = Mappers.getMapper(ClassBatchMapper.class);
	
	ClassBatchDto entityToDto(ClassBatch classBatch);

	ClassBatch dtoToEntity(ClassBatchDto classBatchDto);

	List<ClassBatchDto> listEntityToListDto(List<ClassBatch> listOfClass);

}
