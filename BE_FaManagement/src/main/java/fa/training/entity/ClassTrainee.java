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
@Table(name = "class_trainee")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClassTrainee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "class_id")
	private Long classId;
	@Column(name = "trainee_id")
	private Long traineeId;

	@ManyToOne
	@JoinColumn(name = "class_id", insertable = false, updatable = false)
	private ClassBatch classBatch;
	@ManyToOne
	@JoinColumn(name = "trainee_id", insertable = false, updatable = false)
	private Trainee trainee;
}
