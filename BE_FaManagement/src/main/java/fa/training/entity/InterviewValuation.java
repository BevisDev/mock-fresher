package fa.training.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "interview_valuation")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class InterviewValuation {
	@Id
	@Column(name = "interview_valuation_id")
	private Long interviewValuationId;

	@Column(name = "trainee_id")
	private Long traineeId;

	@Column(name = "date")
	private LocalDate date;

	@Column(name = "interviewer")
	private Long interviewer;

	@Column(name = "remarks")
	private String remarks;
}
