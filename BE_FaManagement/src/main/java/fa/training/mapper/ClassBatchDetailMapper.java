package fa.training.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import fa.training.entity.ClassBatch;
import fa.training.model.ClassBatchDetailDto;

@Mapper
public interface ClassBatchDetailMapper {
	ClassBatchDetailMapper INSTANCE = Mappers.getMapper(ClassBatchDetailMapper.class);

	@Mapping(source = "location.locationName", target = "locationName")
	@Mapping(source = "budget.budgetName", target = "budgetCode")
	@Mapping(source = "subjectType.subjectTypeName", target = "subjectTypeName")
	@Mapping(source = "subSubjectType.subSubjectTypeName", target = "subSubjectTypeName")
	@Mapping(source = "deliveryType.name", target = "deliveryTypeName")
	@Mapping(source = "formatType.formatTypeName", target = "formatTypeName")
	@Mapping(source = "suppliesPartner.suppliesPartnerName", target = "suppliesPartnerName")
	@Mapping(source = "scope.scopeName", target = "scopeName")
	@Mapping(source = "classBatchType.name", target = "classBatchTypeName")
	@Mapping(source = "position.name", target = "positionName")
	ClassBatchDetailDto entityToDto(ClassBatch ClassBatch);

	ClassBatch dtoToEntity(ClassBatchDetailDto ClassBatchDto);

	List<ClassBatchDetailDto> listEntityToListDto(List<ClassBatch> listOfClassBatch);
}
