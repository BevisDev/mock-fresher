package fa.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fa.training.entity.Status;

public interface StatusRepository extends JpaRepository<Status, Long>{
 
	Status findByStatusName(String status);

}
