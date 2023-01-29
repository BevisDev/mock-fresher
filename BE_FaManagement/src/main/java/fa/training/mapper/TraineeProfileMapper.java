package fa.training.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import fa.training.entity.TraineeProfile;
import fa.training.model.TraineeProfileDto;

@Mapper
public interface TraineeProfileMapper {
	TraineeProfileMapper INSTANCE = Mappers.getMapper(TraineeProfileMapper.class);

	@Mapping(source="faculty.name",target = "facultyName")
	@Mapping(source = "university.name",target = "universityName")
	TraineeProfileDto entityToDto(TraineeProfile traineeProfile);

	TraineeProfile dtoToEntity(TraineeProfileDto traineeProfileDto);

	List<TraineeProfileDto> listEntityToListDto(List<TraineeProfile> listOfTraineeProfile);
	
}
