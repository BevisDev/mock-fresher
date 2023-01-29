package fa.training.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CandidateForm {
	private Long canId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate applicationDate;
	private Long channelId;
	private Long locationId;
	private String status;
	private String history;
	private String fullName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
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
	private String remarks;
	private String account;
	private List<InterviewDto> interviews;
	private List<EntryTestDto> entryTests;

	public String getCVConvert() {
		if (cv == null) {
			return cv;
		} else {
			int lastIndex = cv.lastIndexOf("/") + 1;
			return cv.substring(lastIndex);
		}
	}

	public String getGraduationYearConvert() {
		if (graduationYear != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
			return graduationYear.format(formatter);
		}
		return null;
	}
}
