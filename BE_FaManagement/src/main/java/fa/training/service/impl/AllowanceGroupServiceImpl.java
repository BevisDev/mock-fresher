package fa.training.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.entity.AllowanceGroup;
import fa.training.mapper.AllowanceGroupMapper;
import fa.training.model.AllowanceGroupDto;
import fa.training.repository.AllowanceGroupRepository;
import fa.training.service.AllowanceGroupService;
@Service
public class AllowanceGroupServiceImpl implements AllowanceGroupService {
	@Autowired
	private AllowanceGroupRepository allowanceGroupRepository;
	
	@Override
	public List<AllowanceGroupDto> getAllAllowanceGroup() {
		return AllowanceGroupMapper.INSTANCE.listEnityTolistDto(allowanceGroupRepository.findAll());
	}

	@Override
	public AllowanceGroupDto getAllowanceById(String group) {
		AllowanceGroup allowanceGroup = allowanceGroupRepository.findById(group).get();
		return AllowanceGroupMapper.INSTANCE.entityToDto(allowanceGroup);
	}
	
}
