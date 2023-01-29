package fa.training.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fa.training.mapper.DeliveryTypeMapper;
import fa.training.model.DeliveryTypeDto;
import fa.training.repository.DeliveryTypeRepository;
import fa.training.service.DeliveryTypeService;

@Service
@Transactional(rollbackFor = Exception.class)
public class DeliveryTypeServiceImpl implements DeliveryTypeService{

	@Autowired
	private DeliveryTypeRepository deliveryTypeRepository;

	@Override
	public List<DeliveryTypeDto> getAllDeliveryType() {
		return DeliveryTypeMapper.INSTANCE.listEntityToListDto(deliveryTypeRepository.findAll());
	}
	
	
}
