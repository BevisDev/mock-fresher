package fa.training.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fa.training.entity.TraineeProfile;

public interface TraineeProfileRepository extends JpaRepository<TraineeProfile, Long> {
	public List<TraineeProfile> findByFullName(String fullName);

	public List<TraineeProfile> findByDateOfBirth(LocalDate dob);

	public List<TraineeProfile> findByPhone(String phone);

	public List<TraineeProfile> findByEmail(String email);

	public Optional<TraineeProfile> findByAccount(String account);

	@Query("SELECT u FROM TraineeProfile u where u.account LIKE CONCAT(:account,'%')")
	public List<TraineeProfile> getTraineeProfileByAccount(@Param("account") String account);

	@Query("SELECT u FROM TraineeProfile u JOIN Trainee t ON u.id = t.traineeCandidateProfileId WHERE t.classId = :classId")
	public Page<TraineeProfile> findAllByClassId(Long classId, Pageable pageAble);

	public List<TraineeProfile> findAllByIdIn(List<Long> traineeIds);

	@Query("SELECT u FROM TraineeProfile u WHERE u.id not in :traineeProfiles")
	public Page<TraineeProfile> findAllByNotIn(List<Long> traineeProfiles, Pageable pageAble);

	@Query("SELECT u.id FROM TraineeProfile u JOIN Trainee t ON u.id = t.traineeCandidateProfileId WHERE t.classId = :classId AND u.type =:type")
	public List<Long> findByClassIdByType(Long classId, String type);
	
	@Query("SELECT u FROM TraineeProfile u JOIN User t ON u.account = t.username WHERE t.roleName = :name")
	public List<TraineeProfile> findByRole(String name);
	
	
	public TraineeProfile findByFullNameAndDateOfBirthAndGenderAndUniversityIdAndFacultyIdAndPhoneAndEmail(
			String fullName, LocalDate dateOfBirth, String gender, Long universityId, Long facultyId, String phone,
			String email);

	public Page<TraineeProfile> findByIsDelete(boolean b, Pageable pageAble);
}