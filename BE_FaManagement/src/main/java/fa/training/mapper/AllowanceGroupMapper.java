package fa.training.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import fa.training.entity.AllowanceGroup;
import fa.training.model.AllowanceDto;
import fa.training.model.AllowanceGroupDto;

@Mapper
public interface AllowanceGroupMapper {
	AllowanceGroupMapper INSTANCE = Mappers.getMapper(AllowanceGroupMapper.class);
	
	AllowanceGroupDto entityToDto (AllowanceGroup allowanceGroup);
	AllowanceGroup dtoToEntity (AllowanceDto allowanceGroupDto);
	List<AllowanceGroupDto> listEnityTolistDto (List<AllowanceGroup> allowanceGroups);
}
