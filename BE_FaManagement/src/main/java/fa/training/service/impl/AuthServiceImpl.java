package fa.training.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fa.training.entity.User;
import fa.training.filter.JwtProvider;
import fa.training.mapper.AuthMapper;
import fa.training.model.AuthDto;
import fa.training.model.UserDetailsDto;
import fa.training.repository.UserRepository;
import fa.training.service.AuthService;

@Service
@Transactional(rollbackFor = Exception.class)
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtProvider jwtProvider;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public String login(AuthDto authDto) {
		Authentication authentication = new UsernamePasswordAuthenticationToken(authDto.getUsername(),
				authDto.getPassword());

		authenticationManager.authenticate(authentication);

		// TẠO TOKEN
		User user = userRepository.findByUsername(authDto.getUsername());
		String token = jwtProvider.generateToken(new UserDetailsDto(user));

		return token;
	}

	@Override
	public void register(AuthDto autDto) {
		User user = AuthMapper.INSTANCE.authDtoToUser(autDto);
		String hashed = bCryptPasswordEncoder.encode(autDto.getPassword());
		user.setPassword(hashed);
		userRepository.save(user);
	}

}
