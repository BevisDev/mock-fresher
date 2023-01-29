package fa.training.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuditDto {
	private Long auditId;
	private Long classId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	private String relatedPartyOrPeople;
	private String action;
	private String pic;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate deadline;
	private String note;
	private Long eventCategoryId;
	private String eventCategoryName;
}
