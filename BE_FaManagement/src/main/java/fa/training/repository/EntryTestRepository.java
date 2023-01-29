package fa.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fa.training.entity.EntryTest;

@Repository
public interface EntryTestRepository extends JpaRepository<EntryTest, Long> {
	public List<EntryTest> findByIsDeleteAndCandidateId(boolean isDelete, Long candidateId);
	public List<EntryTest> findByCandidateId(Long candidateId);
}
