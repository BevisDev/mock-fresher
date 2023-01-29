package fa.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fa.training.entity.ClassAdminProfile;
import fa.training.entity.TrainerProfile;

public interface ClassAdminProfileRepository extends JpaRepository<ClassAdminProfile, Long> {

	@Query("select a from ClassAdminProfile a join ClassAdmin b on a.id = b.profileId where b.classId = :classId")
	List<ClassAdminProfile> findByClassId(Long classId);

	@Query("select a from ClassAdminProfile a where a.userId = :userId")
	ClassAdminProfile findByUserId(Long userId);

	@Query("select a from ClassAdminProfile a join User b on a.userId = b.id where b.roleName LIKE :roleName")
	public List<ClassAdminProfile> findDeliveryManagerByRoleName(String roleName);
}
