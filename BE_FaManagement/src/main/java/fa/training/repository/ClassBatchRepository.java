package fa.training.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fa.training.entity.ClassBatch;

public interface ClassBatchRepository extends JpaRepository<ClassBatch, Long> {

	@Query(value="select count(a.skill) from ClassBatch a where EXTRACT(YEAR FROM a.expectedStartDate) =  EXTRACT(YEAR FROM :date) and a.skill = :skill and a.locationID = :locationId")
	public int getCountOfSkillOfYear(@Param("date") LocalDate localdate, String skill, Long locationId);
	
	public Optional<ClassBatch> findByIsDeleteAndClassId(boolean isDelete, Long classId);
}
