package fa.training.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TraineeUpdateStatusDto {

	private Long traineeProifileId;
	private Long classId;
	private String statusName;
}
