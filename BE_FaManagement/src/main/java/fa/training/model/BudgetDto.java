package fa.training.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BudgetDto {

	private Long budgetId;
	private String budgetCode;
	private String remarks;
}
