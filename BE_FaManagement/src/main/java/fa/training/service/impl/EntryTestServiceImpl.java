package fa.training.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.mapper.EntryTestMapper;
import fa.training.model.EntryTestDto;
import fa.training.repository.EntryTestRepository;
import fa.training.service.EntryTestService;

@Service
public class EntryTestServiceImpl implements EntryTestService {
	@Autowired
	private EntryTestRepository entryTestRepository;

	@Override
	public List<EntryTestDto> getAllEntryTestsByCandidateId(Long id) {
		return EntryTestMapper.INSTANCE
				.listEntityToListDto(entryTestRepository.findByIsDeleteAndCandidateId(false, id));
	}

}
