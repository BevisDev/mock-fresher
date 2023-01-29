package fa.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fa.training.entity.Interview;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {
	public List<Interview> findByIsDeleteAndCandidateId(boolean isDelete, Long candidateId);

	public List<Interview> findByCandidateId(Long candidateId);
}
