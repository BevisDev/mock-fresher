package fa.training.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import fa.training.entity.EntryTest;
import fa.training.model.EntryTestDto;

@Mapper
public interface EntryTestMapper {
	EntryTestMapper INSTANCE = Mappers.getMapper(EntryTestMapper.class);

	@Mapping(source = "result", target = "resultEntry")
	EntryTestDto entityToDto(EntryTest entryTest);

	@Mapping(source = "resultEntry", target = "result")
	EntryTest dtoToEntity(EntryTestDto entryTestDto);

	List<EntryTestDto> listEntityToListDto(List<EntryTest> entryTests);
}
