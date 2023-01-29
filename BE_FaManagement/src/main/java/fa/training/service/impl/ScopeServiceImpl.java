package fa.training.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fa.training.mapper.ScopeMapper;
import fa.training.model.ScopeDto;
import fa.training.repository.ScopeRepository;
import fa.training.service.ScopeService;

@Service
@Transactional(rollbackFor = Exception.class)
public class ScopeServiceImpl implements ScopeService{

	@Autowired
	private ScopeRepository scopeRepository;
	
	@Override
	public List<ScopeDto> getAllScope() {
		return ScopeMapper.INSTANCE.listEntityToListDto(scopeRepository.findAll());
	}
}

