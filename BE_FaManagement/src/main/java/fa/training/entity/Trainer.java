package fa.training.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "trainer")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Trainer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "trainer_id")
	private Long trainerId;
	@Column(name = "trainer_profile_id")
	private Long trainerProfileId;
	@Column(name="class_id")
	private Long classId;
	@Column(name = "type")
	private Integer type;
	@Column(name = "remarks")
	private String remarks;

	@ManyToOne
	@JoinColumn(name="trainer_profile_id",insertable = false,updatable = false)
	private TrainerProfile trainer;
	@ManyToOne
	@JoinColumn(name="class_id",insertable = false, updatable = false)
	private ClassBatch classBatch;
	
}
