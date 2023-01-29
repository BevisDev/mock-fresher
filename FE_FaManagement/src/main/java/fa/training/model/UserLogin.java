package fa.training.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserLogin {

	private String username;
	private String role;
	private String roleDesc;
}
