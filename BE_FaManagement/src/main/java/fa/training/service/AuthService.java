package fa.training.service;

import fa.training.model.AuthDto;

public interface AuthService {

	public String login(AuthDto authDto);
	public void register(AuthDto autDto);
}
