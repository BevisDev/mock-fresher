package fa.training.entity;

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
@Table(name = "guarantee_id")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Guarantee {
	@Id
	@Column(name = "guarantee_id")
	private Long guaranteeId;

	@Column(name = "trainee_id")
	private Long traineeId;

	@Column(name = "fsu")
	private String fsu;

	@Column(name = "comments")
	private String comments;
	
	@ManyToOne
	@JoinColumn(name="trainee_id",insertable = false, updatable = false)
	private Trainee trainee;
}
