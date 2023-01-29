package fa.training.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fa.training.mapper.BudgetMapper;
import fa.training.model.BudgetDto;
import fa.training.repository.BudgetRepository;
import fa.training.service.BudgetService;

@Service
@Transactional(rollbackFor = Exception.class)
public class BudgetServiceImpl implements BudgetService{

	@Autowired
	BudgetRepository budgetRepository;
	
	@Override
	public List<BudgetDto> getAllBudget() {
		return BudgetMapper.INSTANCE.listEntityToListDto(budgetRepository.findAll());
	}

	
}
