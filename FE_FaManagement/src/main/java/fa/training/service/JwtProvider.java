package fa.training.service;

import java.util.Base64;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import fa.training.model.UserLogin;
import lombok.Data;

@Component
@Data
public class JwtProvider {

	public String getPayload(String token) {
		return new String(Base64.getUrlDecoder().decode(token.split("\\.")[1]));
	}

	public UserLogin getUserLogin(String token)
	{
		JSONObject payload = new JSONObject(getPayload(token));
		return new UserLogin(payload.getString("sub"),payload.getString("role"),payload.getString("roleDescription"));
	}
	
}
