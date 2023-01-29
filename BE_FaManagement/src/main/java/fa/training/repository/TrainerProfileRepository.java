package fa.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fa.training.entity.TrainerProfile;

@Repository
public interface TrainerProfileRepository extends JpaRepository<TrainerProfile, Long>{

	@Query("select a from TrainerProfile a join Trainer b on a.trainerProfileId = b.trainerProfileId where b.classId = :classId and b.type = 1")
	public TrainerProfile findMasterTrainerByClassId(Long classId);
	
	@Query("select a from TrainerProfile a join Trainer b on a.trainerProfileId = b.trainerProfileId where b.classId = :classId and b.type = 0")
	public List<TrainerProfile> findTrainerByClassId(Long classId);
}
