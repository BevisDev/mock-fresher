package fa.training.model;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AuthDto {

	@NotEmpty(message = "Username must be not empty!")
	private String username;
	@NotEmpty(message = "Password must be not empty!")
	private String password;
	private String roleName;
}
