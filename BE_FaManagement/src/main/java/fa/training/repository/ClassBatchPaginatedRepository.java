package fa.training.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import fa.training.entity.ClassBatch;

public interface ClassBatchPaginatedRepository extends PagingAndSortingRepository<ClassBatch, Long>{
	
}
