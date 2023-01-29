package fa.training.service;

import java.util.List;

import fa.training.model.AllowanceGroupDto;

public interface AllowanceGroupService {

	List<AllowanceGroupDto> getAllAllowanceGroup();

	AllowanceGroupDto getAllowanceById(String group);

}
