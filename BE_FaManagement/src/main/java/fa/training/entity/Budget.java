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
@Table(name = "budget")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Budget {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "budget_id")
	private Long budgetId;
	@Column(name = "remarks")
	private String remarks;
	@Column(name = "budget_name")
	private String budgetName;
}
