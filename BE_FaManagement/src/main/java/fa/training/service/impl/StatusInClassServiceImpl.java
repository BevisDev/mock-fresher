package fa.training.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fa.training.mapper.StatusInClassMapper;
import fa.training.model.StatusInClassDto;
import fa.training.repository.StatusInClassRepository;
import fa.training.service.StatusInClasService;

@Service
@Transactional(rollbackFor = Exception.class)
public class StatusInClassServiceImpl implements StatusInClasService{

	@Autowired
	private StatusInClassRepository statusRepository;
	
	@Override
	public List<StatusInClassDto> getAllStatus() {
		return StatusInClassMapper.INSTANCE.listEntityToListDto(statusRepository.findAll());
	}
	

}
