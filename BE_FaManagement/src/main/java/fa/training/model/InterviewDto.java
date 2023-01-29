package fa.training.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InterviewDto {
	private Long id;
	private Long time;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	private String interviewer;
	private String comment;
	private String result;
	private Long candidateId;
	private boolean isDelete;
}
