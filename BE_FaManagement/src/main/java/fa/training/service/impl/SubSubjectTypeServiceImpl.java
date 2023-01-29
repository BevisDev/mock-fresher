package fa.training.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fa.training.mapper.SubSubjectTypeMapper;
import fa.training.model.SubSubjectTypeDto;
import fa.training.repository.SubSubjectTypeRepository;
import fa.training.service.SubSubjectTypeService;

@Service
@Transactional(rollbackFor = Exception.class)
public class SubSubjectTypeServiceImpl implements SubSubjectTypeService {

	@Autowired
	SubSubjectTypeRepository subSubjectTypeRepository;

	@Override
	public List<SubSubjectTypeDto> getAllSubSubjectType() {
		return SubSubjectTypeMapper.INSTANCE.listEntityToListDto(subSubjectTypeRepository.findAll());
	}

}
