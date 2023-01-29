package fa.training.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AllowanceDto {
	private Long allowanceId;
	private String group;
	private Long classTraineeId;
	private Integer allowanceResult;
	private String remarks;
}
