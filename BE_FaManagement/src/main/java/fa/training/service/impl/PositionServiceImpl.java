package fa.training.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fa.training.mapper.PositionMapper;
import fa.training.model.PositionDto;
import fa.training.repository.PositionRepository;
import fa.training.service.PositionService;

@Service
@Transactional(rollbackFor = Exception.class)
public class PositionServiceImpl implements PositionService{

	@Autowired
	private PositionRepository positionRepository;
	
	@Override
	public List<PositionDto> getAllPosition() {
		return PositionMapper.INSTANCE.listEntityToListDto(positionRepository.findAll());
	}

}
