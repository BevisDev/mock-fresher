package fa.training.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import fa.training.entity.Faculty;
import fa.training.model.FacultyDto;

@Mapper
public interface FacultyMapper {
	FacultyMapper INSTANCE = Mappers.getMapper(FacultyMapper.class);

	FacultyDto entityToDto(Faculty faculty);

	Faculty dtoToEntity(FacultyDto facultyDto);

	List<FacultyDto> listEntityToListDto(List<Faculty> Faculties);
}
