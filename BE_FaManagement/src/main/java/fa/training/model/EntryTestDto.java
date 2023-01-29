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
public class EntryTestDto {
	private Long testId;
	private Long time;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	private String languageValuator;
	private String languageResult;
	private String technicalValuator;
	private String technicalResult;
	private String resultEntry;
	private Long candidateId;
	private boolean isDelete;
}
