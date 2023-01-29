package fa.training.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import fa.training.entity.Allowance;
import fa.training.model.AllowanceDto;

@Mapper
public interface AllowanceMapper {
	AllowanceMapper INSTANCE = Mappers.getMapper(AllowanceMapper.class);
	
	AllowanceDto entityToDto (Allowance allowance);
	Allowance dtoToEntity(AllowanceDto allowanceDto);
	
	List<AllowanceDto> listEntityToListDto (List<Allowance> allowances);
}
