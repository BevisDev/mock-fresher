package fa.training.service;

import java.util.List;

import fa.training.model.UniversityDto;

public interface UniversityService {
	public List<UniversityDto> getAllUniversities();

	public UniversityDto getUniversityById(Long id);

	
}
