package fa.training.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fa.training.model.CandidateForm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "trainee_profile")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TraineeProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "trainee_profile_id")
	private Long id;
	@Column(name = "full_name")
	private String fullName;
	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;
	@Column(name = "gender")
	private String gender;
	@Column(name = "university_id")
	private Long universityId;
	@Column(name = "faculty_id")
	private Long facultyId;
	@Column(name = "graduation_year")
	private LocalDate graduationYear;
	@Column(name = "phone")
	private String phone;
	@Column(name = "email")
	private String email;
	@Column(name = "type")
	private String type;
	@Column(name = "skill")
	private String skill;
	@Column(name = "foreign_language")
	private String foreignLanguage;
	@Column(name = "level")
	private String level;
	@Column(name = "cv")
	private String cv;
	@Column(name = "allocation_status")
	private String allocationStatus;
	@Column(name = "remarks")
	private String remarks;
	@Column(name = "account")
	private String account;
	@Column(name="tpb_account")
	private String tpbAcount;
	@Column(name="status")
	private String status;
	@Column(name="history")
	private String history;
	@Column(name="salary_paid")
	private Boolean salaryPaid;
	@Column(name="is_delete")
	private Boolean isDelete;

	@ManyToOne
	@JoinColumn(name = "university_id", insertable = false, updatable = false, nullable = false)
	private University university;
	@ManyToOne
	@JoinColumn(name = "faculty_id", insertable = false, updatable = false, nullable = false)
	private Faculty faculty;

	public TraineeProfile(CandidateForm candidateForm) {
		updateTraineeProfile(candidateForm);
	}

	public void updateTraineeProfile(CandidateForm candidateForm) {
		this.fullName = candidateForm.getFullName();
		this.dateOfBirth = candidateForm.getDateOfBirth();
		this.gender = candidateForm.getGender();
		this.universityId = candidateForm.getUniversityId();
		this.facultyId = candidateForm.getFacultyId();
		this.graduationYear = candidateForm.getGraduationYear();
		this.phone = candidateForm.getPhone();
		this.email = candidateForm.getEmail();
		this.skill = candidateForm.getSkill();
		this.foreignLanguage = candidateForm.getForeignLanguage();
		this.level = candidateForm.getLevel();
		this.cv = candidateForm.getCv();
	}
}

