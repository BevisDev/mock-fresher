package fa.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fa.training.entity.EventCategory;

public interface EventCategoryRepository extends  JpaRepository<EventCategory, Long>{

}
