package fa.training.service;

import java.util.List;

import fa.training.model.EntryTestDto;

public interface EntryTestService {
	public List<EntryTestDto> getAllEntryTestsByCandidateId(Long id);
}
