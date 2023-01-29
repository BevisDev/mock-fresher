package fa.training.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fa.training.model.EntryTestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "entry_test")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EntryTest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "test_id")
	private Long testId;
	private Long time;
	private LocalDate date;
	@Column(name = "language_valuator")
	private String languageValuator;
	@Column(name = "language_result")
	private String languageResult;
	@Column(name = "techical_valuator")
	private String technicalValuator;
	@Column(name = "technical_result")
	private String technicalResult;
	@Column(name = "result")
	private String result;
	@Column(name = "remarks")
	private String remarks;
	@Column(name = "candidate_id")
	private Long candidateId;
	@Column(name = "is_delete")
	private boolean isDelete;
	@ManyToOne
	@JoinColumn(name = "candidate_id", insertable = false, updatable = false)
	private Candidate candidate;

	public void updateEntryTest(EntryTestDto entryDto) {
		this.date = entryDto.getDate();
		this.languageValuator = entryDto.getLanguageValuator();
		this.languageResult = entryDto.getLanguageResult();
		this.technicalValuator = entryDto.getTechnicalValuator();
		this.technicalResult = entryDto.getTechnicalResult();
		this.result = entryDto.getResultEntry();
	}
}
