package fa.training.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import fa.training.entity.Scope;
import fa.training.model.ScopeDto;

@Mapper
public interface ScopeMapper {

	ScopeMapper INSTANCE = Mappers.getMapper(ScopeMapper.class);

	ScopeDto entityToDto(Scope scope);

	Scope dtoToEntity(ScopeDto scopeDto);

	List<ScopeDto> listEntityToListDto(List<Scope> listOfScope);
}
