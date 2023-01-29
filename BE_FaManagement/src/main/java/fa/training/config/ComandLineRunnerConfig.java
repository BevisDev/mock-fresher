package fa.training.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fa.training.model.AuthDto;
import fa.training.model.ChannelDto;
import fa.training.model.FacultyDto;
import fa.training.model.LocationDto;
import fa.training.model.RoleDto;
import fa.training.model.UniversityDto;
import fa.training.service.AuthService;
import fa.training.service.ChannelService;
import fa.training.service.FacultyService;
import fa.training.service.LocationService;
import fa.training.service.RoleService;
import fa.training.service.UniversityService;

//@Configuration
public class ComandLineRunnerConfig {

	@Bean
	CommandLineRunner commandLineRunner(ChannelService channelService, FacultyService facultyService,
			LocationService locationService, UniversityService universityService, AuthService authService,
			RoleService roleService) {
		return args -> {
			channelService.addChannel(new ChannelDto(Long.valueOf(1), "Mail TD"));
			channelService.addChannel(new ChannelDto(Long.valueOf(2), "Mail RUF"));
			facultyService.addFaculty(new FacultyDto(Long.valueOf(1), "Other"));
			facultyService.addFaculty(new FacultyDto(Long.valueOf(2), "Mechatronics"));
			facultyService.addFaculty(new FacultyDto(Long.valueOf(3), "Software Engineering"));
			locationService.addLocation(new LocationDto(Long.valueOf(1), "", "All"));
			locationService.addLocation(new LocationDto(Long.valueOf(2), "", "HCM"));
			locationService.addLocation(new LocationDto(Long.valueOf(3), "", "HN"));
			locationService.addLocation(new LocationDto(Long.valueOf(4), "", "DN"));
			universityService.addUniversity(new UniversityDto(Long.valueOf(1), "Other"));
			universityService.addUniversity(new UniversityDto(Long.valueOf(2), "NLU"));
			universityService.addUniversity(new UniversityDto(Long.valueOf(3), "FPT"));
			roleService.addRole(new RoleDto("ROLE_SYSTEM", "Role for system"));
			//authService.register(new AuthDto("manager", "manager", "ROLE_SYSTEM"));
//			roleService.addRole(new RoleDto("ROLE_SYSTEM", "Role for system"));
//			authService.register(new AuthDto("manager", "manager", "ROLE_SYSTEM"));
		};
	}

}
