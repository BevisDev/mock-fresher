package fa.training.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fa.training.mapper.SupplierPartnerMapper;
import fa.training.model.SupplierPartnerDto;
import fa.training.repository.SupplierPartnerRepository;
import fa.training.service.SupplierPartnerService;

@Service
@Transactional(rollbackFor = Exception.class)
public class SupplierPartnerServiceImpl implements SupplierPartnerService{

	@Autowired
	private SupplierPartnerRepository partnerRepository;
	
	@Override
	public List<SupplierPartnerDto> getAllSupplierPartner() {
		return SupplierPartnerMapper.INSTANCE.listEntityToListDto(partnerRepository.findAll());
	}

	
	
}
