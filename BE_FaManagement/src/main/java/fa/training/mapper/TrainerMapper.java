package fa.training.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import fa.training.entity.TrainerProfile;
import fa.training.model.TrainerDto;

@Mapper
public interface TrainerMapper {
	TrainerMapper INSTANCE = Mappers.getMapper(TrainerMapper.class);

	TrainerDto entityToDto(TrainerProfile trainer);

	TrainerProfile dtoToEntity(TrainerDto trainerDto);

	List<TrainerDto> listEntityToListDto(List<TrainerProfile> listOfTrainer);
}
