package fa.training.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import fa.training.entity.Status;
import fa.training.model.StatusInClassDto;

@Mapper
public interface StatusInClassMapper {

	StatusInClassMapper INSTANCE = Mappers.getMapper(StatusInClassMapper.class);

	StatusInClassDto entityToDto(Status status);

	Status dtoToEntity(StatusInClassDto statusDto);

	List<StatusInClassDto> listEntityToListDto(List<Status> listOfStatus);
}
