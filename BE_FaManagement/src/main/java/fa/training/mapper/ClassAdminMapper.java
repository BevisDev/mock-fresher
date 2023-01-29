package fa.training.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import fa.training.entity.ClassAdminProfile;
import fa.training.model.ClassAdminDto;

@Mapper
public interface ClassAdminMapper {

	ClassAdminMapper INSTANCE = Mappers.getMapper(ClassAdminMapper.class);

	ClassAdminDto entityToDto(ClassAdminProfile classAdmin);

	ClassAdminProfile dtoToEntity(ClassAdminDto classAdminDto);

	List<ClassAdminDto> listEntityToListDto(List<ClassAdminProfile> listOfAdmin);
}
