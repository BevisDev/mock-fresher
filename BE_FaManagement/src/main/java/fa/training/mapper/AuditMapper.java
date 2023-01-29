package fa.training.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import fa.training.entity.Audit;
import fa.training.model.AuditDto;

@Mapper
public interface AuditMapper {
	AuditMapper INSTANCE = Mappers.getMapper(AuditMapper.class);

	@Mapping(source = "eventCategory.name",target = "eventCategoryName")
	AuditDto entityToDto(Audit Audit);

	Audit dtoToEntity(AuditDto AuditDto);

	List<AuditDto> listEntityToListDto(List<Audit> listOfAudit);
	
	List<Audit> listDtoToListEntity(List<AuditDto> listOfAuditDto);

}
