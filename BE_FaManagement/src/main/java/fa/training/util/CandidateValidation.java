package fa.training.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fa.training.model.CandidateForm;
import fa.training.model.EntryTestDto;
import fa.training.model.InterviewDto;
import fa.training.service.TraineeProfileService;

@Component
public class CandidateValidation {
	@Autowired
	private TraineeProfileService traineeProfileService;

	public MESSAGE createCandidateValidation(CandidateForm candidateForm) {
		if (isMandatoryBlank(candidateForm)) {
			return MESSAGE.MSG4;
		} else if (!candidateForm.getPhone().startsWith("0")) {
			return MESSAGE.MSG9;
		} else if (candidateForm.getPhone().length() < 10 && candidateForm.getPhone().length() > 14) {
			return MESSAGE.MSG24;
		} else if (traineeProfileService.isDuplicateProfile(candidateForm)) {
			return MESSAGE.MSG13;
		}
		return null;
	}

	public boolean isMandatoryBlank(CandidateForm candidateForm) {
		return candidateForm.getFullName().isEmpty() || candidateForm.getDateOfBirth() == null
				|| candidateForm.getPhone().isEmpty() || candidateForm.getEmail().isEmpty();
	}

	public boolean isHasInterview(InterviewDto interviewDto) {
		return interviewDto.getDate() != null || !interviewDto.getComment().isEmpty()
				|| !interviewDto.getInterviewer().isEmpty();
	}

	public boolean isHasEntryTest(EntryTestDto entryTestDto) {
		return entryTestDto.getDate() != null || !entryTestDto.getLanguageResult().isEmpty()
				|| !entryTestDto.getLanguageValuator().isEmpty() || !entryTestDto.getTechnicalResult().isEmpty()
				|| !entryTestDto.getTechnicalValuator().isEmpty();
	}

	public MESSAGE updateCandidateValidation(CandidateForm candidateForm) {
		if (isMandatoryBlank(candidateForm)) {
			return MESSAGE.MSG4;
		} else if (!candidateForm.getPhone().startsWith("0")) {
			return MESSAGE.MSG9;
		} else if (candidateForm.getPhone().length() < 10 && candidateForm.getPhone().length() > 14) {
			return MESSAGE.MSG24;
		}
		return null;
	}

}
