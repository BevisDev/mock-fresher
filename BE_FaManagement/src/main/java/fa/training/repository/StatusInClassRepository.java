package fa.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fa.training.entity.Status;

public interface StatusInClassRepository extends JpaRepository<Status, Long> {

	public Status findByStatusName(String statusName);
}
