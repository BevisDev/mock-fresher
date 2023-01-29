package fa.training.service;

import javax.servlet.http.HttpSession;

import fa.training.model.AuthDto;

public interface AuthService {

	public boolean login(AuthDto authDto, HttpSession session);
}
