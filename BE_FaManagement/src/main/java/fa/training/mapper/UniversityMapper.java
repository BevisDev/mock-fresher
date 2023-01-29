package fa.training.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import fa.training.entity.University;
import fa.training.model.UniversityDto;

@Mapper
public interface UniversityMapper {
	UniversityMapper INSTANCE = Mappers.getMapper(UniversityMapper.class);

	UniversityDto entityToDto(University university);

	University dtoToEntity(UniversityDto universityDto);

	List<UniversityDto> listEntityToListDto(List<University> Universities);
}
