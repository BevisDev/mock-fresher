package fa.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import fa.training.entity.BudgetDetail;

public interface BudgetDetailRepository extends JpaRepository<BudgetDetail, Long>{

	@Query("select sum(a.sum) from BudgetDetail a where a.classId = :classId")
	public Float caculateTotalBudgetByClassId(Long classId);
	public List<BudgetDetail> findAllByClassId(Long classId);
	@Transactional
	@Modifying
	@Query("delete from BudgetDetail b where b not in :budgetDetails and b.classId = :classId")
	public void deleteAllNotIn(List<BudgetDetail> budgetDetails, Long classId);
	@Transactional
	@Modifying
	@Query("delete from BudgetDetail b where b.classId = :classId")
	public void deleteAllByClassId(Long classId);
}
