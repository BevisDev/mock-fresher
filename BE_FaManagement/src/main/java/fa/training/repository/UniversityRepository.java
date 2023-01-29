package fa.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fa.training.entity.University;

public interface UniversityRepository extends JpaRepository<University, Long>{
	public List<University> findByIsDelete(boolean isDelete);
	public University findByName(String name);
	
}
