package fa.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fa.training.entity.AllowanceGroup;

public interface AllowanceGroupRepository extends JpaRepository<AllowanceGroup, String> {

}
