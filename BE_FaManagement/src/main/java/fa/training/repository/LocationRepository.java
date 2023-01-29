package fa.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fa.training.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Long>{
	public List<Location> findByIsDelete(boolean isDelete);
}
