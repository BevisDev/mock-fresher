package fa.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import fa.training.entity.Trainer;

public interface ClassTrainerRepository extends JpaRepository<Trainer, Long> {

	@Transactional
	@Modifying
	@Query("delete from Trainer t where t.classId = :classId")
	public void deleleAllByClassId(Long classId);
}
