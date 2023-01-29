package fa.training.model;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClassBatchDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long classId;

	private String className;

	private String classCode;

	private Long locationID;

	private String detailLocation;

	private LocalDate actualStartDate;

	private LocalDate actualEndDate;

	private String status;

	private String remarks;

}
