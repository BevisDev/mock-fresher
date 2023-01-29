package fa.training.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fa.training.entity.Trainee;

public interface TraineeRepository extends JpaRepository<Trainee, Long> {
	
	public List<Trainee> findAllByClassIdAndTraineeCandidateProfileIdIn(Long classId,List<Long> traineeIds);

	public List<Trainee> findAllByTraineeCandidateProfileIdIn(List<Long> traineeIds);

	public Trainee findByClassIdAndTraineeCandidateProfileId(Long classId, Long profileId);

}
