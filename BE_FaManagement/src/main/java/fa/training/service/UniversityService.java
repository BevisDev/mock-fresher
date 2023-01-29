package fa.training.service;

import java.util.List;

import fa.training.model.UniversityDto;

public interface UniversityService {
	public List<UniversityDto> getAllUniversities();

	public boolean addUniversity(UniversityDto universityDto);

	public UniversityDto getUniversityById(Long id);
}
