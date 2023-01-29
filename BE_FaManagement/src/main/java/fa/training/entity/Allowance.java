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
@Table(name = "allowance")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Allowance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "allowance_id",length = 10)
	private Long allowanceId;
	
	@Column(name = "allowance_group")
	private String group;

	@Column(name = "class_trainee_id")
	private Long classTraineeId;

	@Column(name = "allowance_result")
	private Integer allowanceResult;

	@Column(name = "remarks")
	private String remarks;
	
	@ManyToOne
	@JoinColumn(name = "class_trainee_id",insertable = false,updatable = false)
	private ClassTrainee classTrainee;
	@ManyToOne
	@JoinColumn(name="allowance_group",insertable = false,updatable = false)
	private AllowanceGroup allowanceGroup;
}
