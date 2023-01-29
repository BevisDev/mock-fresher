package fa.training.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fa.training.entity.Location;
import fa.training.mapper.LocationMapper;
import fa.training.model.LocationDto;
import fa.training.repository.LocationRepository;
import fa.training.service.LocationService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationRepository locationRepository;

	@Override
	public List<LocationDto> getAllLocation() {
		return LocationMapper.INSTANCE.listEntityToListDto(locationRepository.findByIsDelete(false));
	}

	@Override
	public boolean addLocation(LocationDto locationDto) {
		try {
			Location location = LocationMapper.INSTANCE.dtoToEntity(locationDto);
			location.setDelete(false);
			locationRepository.save(location);
			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}

	}

	@Override
	public LocationDto getLocationById(Long id) {
		Location location = locationRepository.getById(id);
		return LocationMapper.INSTANCE.entityToDto(location);
	}

}
