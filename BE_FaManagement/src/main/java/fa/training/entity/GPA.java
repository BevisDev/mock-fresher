package fa.training.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "gpa")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class GPA {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gpa_id")
	private Long gpaId;

	@Column(name = "class_trainee_id")
	private Long classTraineeId;

	@Column(name = "attendant_id")
	private Long attendantId;

	@Column(name = "allowance_id")
	private Long allowanceId;

	@Column(name = "gpa_result")
	private Double gpaResult;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "gpa_gpa_id")
	private Long gpaGpaId;
	@Column(name = "start_date")
	private LocalDate startDate;
	@Column(name = "end_date")
	private LocalDate endDate;
	@Column(name="reward_or_pen_id")
	private Long rewardOrPenId;

	@ManyToOne
	@JoinColumn(name = "gpa_gpa_id", insertable = false, updatable = false)
	private GPA gpa;
	@ManyToOne
	@JoinColumn(name = "class_trainee_id", insertable = false, updatable = false)
	private ClassTrainee classTrainee;
	@OneToOne
	@JoinColumn(name="reward_or_pen_id",insertable = false,updatable = false)
	private RewardPenalty rewardPenalty;
}
