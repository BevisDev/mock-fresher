package fa.training.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SubjectTypeDto {
	private Long subjectTypeId;
	private String remarks;
	private String subjectTypeName;
}
