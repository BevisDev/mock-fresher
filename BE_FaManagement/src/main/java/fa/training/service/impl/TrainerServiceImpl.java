package fa.training.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fa.training.mapper.TrainerMapper;
import fa.training.model.TrainerDto;
import fa.training.repository.TrainerProfileRepository;
import fa.training.service.TrainerService;

@Service
@Transactional(rollbackFor = Exception.class)
public class TrainerServiceImpl implements TrainerService{

	@Autowired
	TrainerProfileRepository profileRepository;
	
	@Override
	public List<TrainerDto> getAllTrainer() {
		return TrainerMapper.INSTANCE.listEntityToListDto(profileRepository.findAll());
	}

}
