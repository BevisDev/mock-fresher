package fa.training.service;

import java.util.List;

import fa.training.model.FacultyDto;

public interface FacultyService {
	public List<FacultyDto> getAllFaculties();

	public boolean addFaculty(FacultyDto facultyDto);

	public FacultyDto getFacultyById(Long id);
}
