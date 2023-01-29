package fa.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fa.training.entity.Faculty;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
	public List<Faculty> findByIsDelete(boolean isDelete);
	public Faculty findByName(String name);
	
}
