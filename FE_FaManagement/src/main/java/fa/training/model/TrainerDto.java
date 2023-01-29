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
public class TrainerDto {
	private Long trainerProfileId;
	private String fullName;
	private Long userId;
	private LocalDate dateOfBirth;
	private String gender;
	private String unit;
	private String major;
	private String phone;
	private String email;
	private Integer experience;
	private String marks;

}
