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
@Table(name="budget_detail")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BudgetDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String item;
	private String unit;
	@Column(name="unit_expense")
	private Float unitExpense;
	private Integer quantity;
	private Float amount;
	private Float tax;
	private Float sum;
	private String note;
	@Column(name="class_id")
	private Long classId;
	@Column(name="budget_id")
	private Long budgetId;
	
	@ManyToOne
	@JoinColumn(name = "class_id",insertable = false,updatable = false)
	private ClassBatch classBatch;
	
}
