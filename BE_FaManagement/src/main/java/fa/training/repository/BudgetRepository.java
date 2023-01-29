package fa.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fa.training.entity.Budget;

public interface BudgetRepository extends JpaRepository<Budget, Long>{

}
