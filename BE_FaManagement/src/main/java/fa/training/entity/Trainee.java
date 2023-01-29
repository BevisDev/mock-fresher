package fa.training.entity;

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
@Table(name = "trainee")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Trainee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "trainee_candidate_id")
	private Long traineeCandidateId;
	@Column(name = "trainee_candidate_profile_id")
	private Long traineeCandidateProfileId;
	@Column(name="class_id")
	private Long classId;
	@Column(name = "attendant_status_id")
	private Long attendantStatusId;
	@Column(name = "allowance_group")
	private Long group;
	@Column(name = "gpa_id")
	private Long gpaId;
	@Column(name = "reward_penalty_id")
	private int rewardPenaltyId;
	@Column(name = "learning_path_id")
	private int learningPathId;
	@Column(name = "guarantee_id")
	private Integer guaranteeId;
	@Column(name = "interview_valuation_id")
	private Integer interviewValuationId;
	@Column(name = "status_id")
	private Long statusId;
	@Column(name = "remarks")
	private String remarks;
	@Column(name="allocation_id")
	private Long allocationId;
	@Column(name="is_delete")
	private boolean isDelete;
	@Column(name="history")
	private String history;
	
	@ManyToOne
	@JoinColumn(name = "status_id",insertable = false,updatable = false)
	private Status status;
	@OneToOne
	@JoinColumn(name="allocation_id",insertable = false, updatable = false)
	private Allocation allocation;
	@ManyToOne
	@JoinColumn(name="class_id",insertable = false,updatable = false)
	private ClassBatch classBatch;
	@ManyToOne
	@JoinColumn(name="trainee_candidate_profile_id",insertable = false,updatable = false)
	private TraineeProfile traineeProfile;	
}
