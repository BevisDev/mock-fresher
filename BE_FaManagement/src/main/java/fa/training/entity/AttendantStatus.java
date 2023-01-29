package fa.training.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "attendant_status")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AttendantStatus {
	@Id
	@Column(name = "attendant_id")
	private Long attendantId;

	@Column(name = "trainee_id")
	private Long traineeId;

	@Column(name = "discipline_point")
	private Integer disciplinePoint;

	@Column(name = "milestones")
	private LocalDate milestones;

	@Column(name = "remarks", nullable = false)
	private String remarks;
	
	@ManyToOne
	@JoinColumn(name="trainee_id",insertable = false,updatable = false)
	private Trainee trainee;
}
