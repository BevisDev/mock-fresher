package fa.training.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RestCreateCandidate {
	private CandidateForm candidateForm;
	private InterviewDto interviewDto;
	private EntryTestDto entryTestDto;
}
