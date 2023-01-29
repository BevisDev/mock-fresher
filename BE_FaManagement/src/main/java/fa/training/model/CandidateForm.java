package fa.training.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import fa.training.entity.Candidate;
import fa.training.entity.TraineeProfile;
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
	private LocalDate dateOfBirth;
	private String gender;
	private Long universityId;
	private Long facultyId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
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

	public CandidateForm(Candidate candidate, TraineeProfile traineeProfile) {
		super();
		canId = candidate.getId();
		applicationDate = candidate.getApplicationDate();
		status = candidate.getStatus();
		channelId = candidate.getChannelId();
		locationId = candidate.getLocationId();
		history = candidate.getHistory();
		remarks = candidate.getRemarks();
		fullName = traineeProfile.getFullName();
		dateOfBirth = traineeProfile.getDateOfBirth();
		gender = traineeProfile.getGender();
		universityId = traineeProfile.getUniversityId();
		facultyId = traineeProfile.getFacultyId();
		graduationYear = traineeProfile.getGraduationYear();
		phone = traineeProfile.getPhone();
		email = traineeProfile.getEmail();
		type = traineeProfile.getType();
		skill = traineeProfile.getSkill();
		foreignLanguage = traineeProfile.getForeignLanguage();
		level = traineeProfile.getLevel();
		cv = traineeProfile.getCv();
		account = traineeProfile.getAccount();
	}
}
