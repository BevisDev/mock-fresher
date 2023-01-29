package fa.training.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import fa.training.entity.Candidate;
import fa.training.model.CandidateDto;

@Mapper
public interface CandidateMapper {
	CandidateMapper INSTANCE = Mappers.getMapper(CandidateMapper.class);

	CandidateDto entityToDto(Candidate candidate);

	Candidate dtoToEntity(CandidateDto candidateDto);

	List<CandidateDto> listEntityToListDto(List<Candidate> candidates);
}
