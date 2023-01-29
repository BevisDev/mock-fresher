package fa.training.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fa.training.entity.University;
import fa.training.mapper.UniversityMapper;
import fa.training.model.UniversityDto;
import fa.training.repository.UniversityRepository;
import fa.training.service.UniversityService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class UniversityServiceImpl implements UniversityService {
	@Autowired
	private UniversityRepository universityRepository;

	@Override
	public List<UniversityDto> getAllUniversities() {
		return UniversityMapper.INSTANCE.listEntityToListDto(universityRepository.findByIsDelete(false));
	}

	@Override
	public boolean addUniversity(UniversityDto universityDto) {
		try {
			University university = UniversityMapper.INSTANCE.dtoToEntity(universityDto);
			university.setDelete(false);
			universityRepository.save(university);
			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}

	}

	@Override
	public UniversityDto getUniversityById(Long id) {
		University university = universityRepository.getById(id);
		return UniversityMapper.INSTANCE.entityToDto(university);
	}

}
