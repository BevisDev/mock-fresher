package fa.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import fa.training.entity.Audit;

public interface AuditRepository extends JpaRepository<Audit, Long>{

	public List<Audit> findByClassId(Long classId);
	@Transactional
	@Modifying
	@Query("delete from Audit a where a not in :audits and a.classId = :classId")
	public void deleteAllByNotIn(List<Audit> audits, Long classId);
	@Transactional
	@Modifying
	@Query("delete from Audit a where a.classId = :classId")
	public void deleteAllByClassId(Long classId);
}
