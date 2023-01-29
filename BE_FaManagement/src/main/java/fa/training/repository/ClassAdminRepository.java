package fa.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import fa.training.entity.ClassAdmin;

public interface ClassAdminRepository extends JpaRepository<ClassAdmin, Long> {

	@Transactional
	@Modifying
	@Query("delete from ClassAdmin a where a.classId = :classId")
	public void deleteAllByClassId(Long classId);

	@Query("select a from ClassAdmin a where a.classId = :classId")
	List<ClassAdmin> findByClassId(Long classId);
}
