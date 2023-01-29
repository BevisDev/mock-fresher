package fa.training.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BudgetDetailDto {
	private Long id;
	private String item;
	private String unit;
	private Float unitExpense;
	private Integer quantity;
	private Float amount;
	private Float tax;
	private Float sum;
	private String note;
	private Long classId;
	private Long budgetId;
}
