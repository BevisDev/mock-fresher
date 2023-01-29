package fa.training.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fa.training.mapper.SubjectTypeMapper;
import fa.training.model.SubjectTypeDto;
import fa.training.repository.SubjectTypeRepository;
import fa.training.service.SubjectTypeService;

@Service
@Transactional(rollbackFor = Exception.class)
public class SubjectTypeServiceImpl implements SubjectTypeService {

	@Autowired
	private SubjectTypeRepository subjectTypeRepository;

	@Override
	public List<SubjectTypeDto> getAllSubjectType() {
		return SubjectTypeMapper.INSTANCE.listEntityToListDto(subjectTypeRepository.findAll());
	}

}
