package fa.training.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fa.training.entity.User;
import fa.training.model.UserDetailsDto;
import fa.training.repository.UserRepository;

@Service
@Transactional(rollbackOn = Exception.class)
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username);
		if (user == null)
			throw new UsernameNotFoundException("Username is not exist");
		return new UserDetailsDto(user);

	}
}
