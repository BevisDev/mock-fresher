package fa.training.service;

import java.util.List;

import fa.training.model.LocationDto;

public interface LocationService {

	public List<LocationDto> getAllLocation();

	public boolean addLocation(LocationDto locationDto);

	public LocationDto getLocationById(Long id);
}
