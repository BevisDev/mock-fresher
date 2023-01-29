package fa.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fa.training.entity.DeliveryType;

public interface DeliveryTypeRepository extends JpaRepository<DeliveryType, Long>{

}
