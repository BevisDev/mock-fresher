package fa.training.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "trainer_profile")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TrainerProfile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "trainer_profile_id")
	private Long trainerProfileId;
	@Column(name = "trainer_id")
	private Long trainerId;
	@Column(name = "full_name")
	private String fullName;
	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;
	@Column(name = "gender")
	private String gender;
	@Column(name = "unit")
	private String unit;
	@Column(name = "major")
	private String major;
	@Column(name = "phone", unique = true)
	private String phone;
	@Column(name = "email", unique = true)
	private String email;
	@Column(name = "experience")
	private Integer experience;
	@Column(name = "remarks")
	private String marks;
	@Column(name="user_id")
	private Long userId;
	
	@OneToOne
	@JoinColumn(name="user_id",insertable = false,updatable = false, nullable = false)
	private User user;
}
