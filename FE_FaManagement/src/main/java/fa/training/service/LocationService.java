package fa.training.service;

import java.util.List;

import fa.training.model.LocationDto;

public interface LocationService {

	public List<LocationDto> getAllLocation();

	public LocationDto getLocationById(Long id);
}
