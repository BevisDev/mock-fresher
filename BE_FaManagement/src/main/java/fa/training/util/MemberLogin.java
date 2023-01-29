package fa.training.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import fa.training.entity.User;
import fa.training.repository.UserRepository;

@Component
public class MemberLogin {
	
	@Autowired
	private UserRepository userRepository;
	
	public User getUserLogin()
	{
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		return userRepository.findByUsername(username);
	}
}
