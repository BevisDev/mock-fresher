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
@Table(name = "learning_path")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class LearningPath {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "learning_path_id")
	private Long learnPathId;

	@Column(name = "class_trainee_id")
	private Long classTraineeId;

	@Column(name = "topic_id")
	private Long topicId;
	@Column(name = "score")
	private Double score;
	
	@ManyToOne
	@JoinColumn(name="topic_id",insertable = false,updatable = false)
	private Topic topic;
	@ManyToOne
	@JoinColumn(name="class_trainee_id",insertable = false,updatable = false)
	private Trainee trainee;

}
