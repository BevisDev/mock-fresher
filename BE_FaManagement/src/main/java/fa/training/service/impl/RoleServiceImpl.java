package fa.training.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fa.training.entity.Role;
import fa.training.mapper.RoleMapper;
import fa.training.model.RoleDto;
import fa.training.repository.RoleRepository;
import fa.training.service.RoleService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public boolean addRole(RoleDto roleDto) {
		try {
			Role role = RoleMapper.INSTANCE.dtoToEntity(roleDto);
			roleRepository.save(role);
			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
	}
}
