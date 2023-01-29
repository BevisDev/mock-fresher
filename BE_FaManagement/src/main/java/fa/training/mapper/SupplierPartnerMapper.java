package fa.training.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import fa.training.entity.SupplierPartner;
import fa.training.model.SupplierPartnerDto;

@Mapper
public interface SupplierPartnerMapper {
	SupplierPartnerMapper INSTANCE = Mappers.getMapper(SupplierPartnerMapper.class);

	SupplierPartnerDto entityToDto(SupplierPartner SupplierPartner);

	SupplierPartner dtoToEntity(SupplierPartnerDto SupplierPartnerDto);

	List<SupplierPartnerDto> listEntityToListDto(List<SupplierPartner> listOfSupplierPartner);
}
