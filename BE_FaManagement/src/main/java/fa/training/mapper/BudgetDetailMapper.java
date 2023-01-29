package fa.training.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import fa.training.entity.BudgetDetail;
import fa.training.model.BudgetDetailDto;

@Mapper
public interface BudgetDetailMapper {
	BudgetDetailMapper INSTANCE = Mappers.getMapper(BudgetDetailMapper.class);

	BudgetDetailDto entityToDto(BudgetDetail budgetDetail);

	BudgetDetail dtoToEntity(BudgetDetailDto budgetDetailDto);

	List<BudgetDetailDto> listEntityToListDto(List<BudgetDetail> listOfBudgetDetail);
	
	List<BudgetDetail> listDtoToListEntity(List<BudgetDetailDto> listOfBudgetDetailDto);

}
