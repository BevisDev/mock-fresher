package fa.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fa.training.entity.Position;

public interface PositionRepository extends JpaRepository<Position, Long>{

}
