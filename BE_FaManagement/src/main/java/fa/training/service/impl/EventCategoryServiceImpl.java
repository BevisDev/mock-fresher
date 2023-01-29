package fa.training.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fa.training.mapper.EventCategoryMapper;
import fa.training.model.EventCategoryDto;
import fa.training.repository.EventCategoryRepository;
import fa.training.service.EventCategoryService;

@Service
@Transactional(rollbackFor = Exception.class)
public class EventCategoryServiceImpl implements EventCategoryService {

	@Autowired
	private EventCategoryRepository eventCategoryRepository;

	@Override
	public List<EventCategoryDto> getAllEventCategory() {
		return EventCategoryMapper.INSTANCE.listEntityToListDto(eventCategoryRepository.findAll());
	}

}
