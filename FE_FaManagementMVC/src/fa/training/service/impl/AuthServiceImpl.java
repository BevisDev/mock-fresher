package fa.training.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fa.training.model.AuthDto;
import fa.training.model.ResponseObject;
import fa.training.model.UserLogin;
import fa.training.service.AuthService;
import fa.training.service.JwtProvider;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

	@Autowired
	private JwtProvider jwtProvider;
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public boolean login(AuthDto authDto, HttpSession session) {
		boolean rs = false;
		String url = "http://localhost:8100/api/auth/login";
		HttpEntity<AuthDto> request = new HttpEntity<>(authDto);
		try {
			ResponseObject token = restTemplate.postForObject(url, request, ResponseObject.class);
			UserLogin userLogin = jwtProvider.getUserLogin(token.getData().toString());
			session.setAttribute("userLogin", userLogin);
			session.setAttribute("token", token.getData().toString());

			rs = true;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Login failed");
		}
		return rs;
	}

}
