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
public class TraineeProfileDto {
	private Long id;
	private String fullName;
	private LocalDate dateOfBirth;
	private String gender;
	private Long universityId;
	private Long facultyId;
	private LocalDate graduationYear;
	private String phone;
	private String email;
	private String type;
	private String skill;
	private String foreignLanguage;
	private String level;
	private String cv;
	private String allocationStatus;
	private String account;
	private String facultyName;
	private String universityName;
	private String tbpAccount;
	private String commitment;
	private String status;
	private String history;
	private Boolean salaryPaid;
	private String allowanceGroup;
	private Boolean isDelete;
	
	public String getCV() {
		if (cv == null) {
			return cv;
		} else {
			int lastIndex = cv.lastIndexOf("/") + 1;
			return cv.substring(lastIndex);
		}
	}

}
