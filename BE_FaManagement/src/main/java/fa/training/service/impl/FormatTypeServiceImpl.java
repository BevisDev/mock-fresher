package fa.training.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fa.training.mapper.FormatTypeMapper;
import fa.training.model.FormatTypeDto;
import fa.training.repository.FormatTypeRepository;
import fa.training.service.FormatTypeService;

@Service
@Transactional(rollbackFor = Exception.class)
public class FormatTypeServiceImpl implements FormatTypeService {

	@Autowired
	private FormatTypeRepository formatTypeRepository;

	@Override
	public List<FormatTypeDto> getAllFormatType() {
		return FormatTypeMapper.INSTANCE.listEntityToListDto(formatTypeRepository.findAll());
	}

}
