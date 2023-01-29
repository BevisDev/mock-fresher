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
@Table(name = "status")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Status {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "status_id")
	private Long statusId;
	@Column(name = "trainee_id")
	private Long traineeId;
	@Column(name = "remarks")
	private String remarks;
	@Column(name = "class_batch_class_id")
	private Long classId;
	@Column(name="status_name")
	private String statusName;

}
