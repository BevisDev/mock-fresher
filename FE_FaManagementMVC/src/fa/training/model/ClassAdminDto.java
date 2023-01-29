package fa.training.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClassAdminDto {

	private Long id;
	private String fullName;
	private String email;
	private String gender;
	private LocalDate dateOfBirth;
	private String remarks;
	private String phone;
}
