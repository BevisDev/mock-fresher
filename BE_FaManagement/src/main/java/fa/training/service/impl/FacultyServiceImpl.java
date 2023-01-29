package fa.training.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fa.training.entity.Faculty;
import fa.training.mapper.FacultyMapper;
import fa.training.model.FacultyDto;
import fa.training.repository.FacultyRepository;
import fa.training.service.FacultyService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class FacultyServiceImpl implements FacultyService {
	@Autowired
	private FacultyRepository facultyRepository;

	@Override
	public List<FacultyDto> getAllFaculties() {
		return FacultyMapper.INSTANCE.listEntityToListDto(facultyRepository.findByIsDelete(false));
	}

	@Override
	public boolean addFaculty(FacultyDto facultyDto) {
		try {
			Faculty faculty = FacultyMapper.INSTANCE.dtoToEntity(facultyDto);
			faculty.setDelete(false);
			facultyRepository.save(faculty);
			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
	}

	@Override
	public FacultyDto getFacultyById(Long id) {
		Faculty faculty = facultyRepository.getById(id);
		return FacultyMapper.INSTANCE.entityToDto(faculty);
	}

}
