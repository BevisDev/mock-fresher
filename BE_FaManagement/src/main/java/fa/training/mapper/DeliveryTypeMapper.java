package fa.training.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import fa.training.entity.DeliveryType;
import fa.training.model.DeliveryTypeDto;

@Mapper
public interface DeliveryTypeMapper {
	DeliveryTypeMapper INSTANCE = Mappers.getMapper(DeliveryTypeMapper.class);

	DeliveryTypeDto entityToDto(DeliveryType deliveryType);

	DeliveryType dtoToEntity(DeliveryTypeDto deliveryTypeDto);

	List<DeliveryTypeDto> listEntityToListDto(List<DeliveryType> listOfdeliveryType);
}
