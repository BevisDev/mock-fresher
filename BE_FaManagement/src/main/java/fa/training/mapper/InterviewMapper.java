package fa.training.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import fa.training.entity.Interview;
import fa.training.model.InterviewDto;

@Mapper
public interface InterviewMapper {
	InterviewMapper INSTANCE = Mappers.getMapper(InterviewMapper.class);

	InterviewDto entityToDto(Interview interview);

	Interview dtoToEntity(InterviewDto interviewDto);

	List<InterviewDto> listEntityToListDto(List<Interview> interviews);
}
