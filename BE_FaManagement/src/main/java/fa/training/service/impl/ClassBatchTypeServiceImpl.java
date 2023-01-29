package fa.training.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fa.training.mapper.ClassBatchTypeMapper;
import fa.training.model.ClassBatchTypeDto;
import fa.training.repository.ClassBatchTypeRepository;
import fa.training.service.ClassBatchTypeService;

@Service
@Transactional(rollbackFor = Exception.class)
public class ClassBatchTypeServiceImpl implements ClassBatchTypeService {

	@Autowired
	private ClassBatchTypeRepository classTypeRepository;

	@Override
	public List<ClassBatchTypeDto> getAllClassBatchType() {
		// TODO Auto-generated method stub
		return ClassBatchTypeMapper.INSTANCE.listEntityToListDto(classTypeRepository.findAll());
	}

}
