package fa.training.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import fa.training.entity.Budget;
import fa.training.model.BudgetDto;

@Mapper
public interface BudgetMapper {
	BudgetMapper INSTANCE = Mappers.getMapper(BudgetMapper.class);

	@Mapping(source = "budgetName", target = "budgetCode")
	BudgetDto entityToDto(Budget budget);

	@Mapping(source = "budgetCode", target = "budgetName")
	Budget dtoToEntity(BudgetDto budgetDto);

	List<BudgetDto> listEntityToListDto(List<Budget> listOfBudget);
}
