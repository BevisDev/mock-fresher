package fa.training.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import fa.training.entity.Role;
import fa.training.model.RoleDto;

@Mapper
public interface RoleMapper {
	RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

	RoleDto entityToDto(Role role);

	Role dtoToEntity(RoleDto roleDto);

	List<RoleDto> listEntityToListDto(List<Role> roles);
}
