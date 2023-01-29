package fa.training.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CandidateDto {
	private Long id;
	private Long profileId;
	private LocalDate applicationDate;
	private Long channelId;
	private Long locationId;
	private String status;
	private String history;
	private String remarks;
}
