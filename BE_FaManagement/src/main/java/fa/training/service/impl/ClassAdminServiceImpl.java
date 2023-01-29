package fa.training.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fa.training.mapper.ClassAdminMapper;
import fa.training.model.ClassAdminDto;
import fa.training.repository.ClassAdminProfileRepository;
import fa.training.service.ClassAdminService;

@Service
@Transactional(rollbackFor = Exception.class)
public class ClassAdminServiceImpl implements ClassAdminService {

	@Autowired
	ClassAdminProfileRepository adminRepository;

	@Override
	public List<ClassAdminDto> getAllClassAdmin() {

		return ClassAdminMapper.INSTANCE.listEntityToListDto(adminRepository.findAll());
	}
}
