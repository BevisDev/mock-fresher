package fa.training.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import fa.training.entity.EventCategory;
import fa.training.model.EventCategoryDto;

@Mapper
public interface EventCategoryMapper {
	EventCategoryMapper INSTANCE = Mappers.getMapper(EventCategoryMapper.class);

	EventCategoryDto entityToDto(EventCategory eventCategory);

	EventCategory dtoToEntity(EventCategoryDto eventCategoryDto);

	List<EventCategoryDto> listEntityToListDto(List<EventCategory> listOfEventCategory);
}
