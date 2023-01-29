package fa.training.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "topic")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Topic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "topic_id")
	private Long topicId;
	@Column(name = "remarks")
	private String remarks;
	@Column(name = "topic_name")
	private String topicName;
	@Column(name = "pass_score")
	private Double passScore;
	@Column(name = "max_score")
	private Double maxScore;

}
