package fa.training.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "allowance_group")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AllowanceGroup {
	@Id
	@Column(name = "allowance_group")
	private String group;

	@Column(name = "allowance", nullable = false)
	private float allowance;

	@Column(name = "remarks")
	private String remarks;
}
