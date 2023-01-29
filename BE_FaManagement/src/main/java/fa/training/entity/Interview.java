package fa.training.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fa.training.model.InterviewDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "interview")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Interview {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "interview_id")
	private Long id;
	private Long time;
	private LocalDate date;
	private String interviewer;
	private String comment;
	private String result;
	private String remarks;
	@Column(name = "is_delete")
	private boolean isDelete;
	@Column(name = "candidate_id")
	private Long candidateId;
	@ManyToOne
	@JoinColumn(name = "candidate_id", insertable = false, updatable = false)
	private Candidate candidate;

	public void updateInterview(InterviewDto interview) {
		this.date = interview.getDate();
		this.interviewer = interview.getInterviewer();
		this.comment = interview.getComment();
		this.result = interview.getResult();
	}
}
