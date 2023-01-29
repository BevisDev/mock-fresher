package fa.training.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fa.training.entity.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
	public Optional<Candidate> findByIsDeleteAndId(boolean isDelete, Long id);

	public List<Candidate> findByIsDelete(boolean isDelete, Pageable pageable);

	@Query("SELECT COUNT(c.id) FROM Candidate c WHERE c.isDelete = :isDelete")
	public int getAllCandidateAmount(@Param("isDelete") boolean isDelete);
}
