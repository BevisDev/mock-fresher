package fa.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fa.training.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	public User findByUsername(String username);

	public List<User> findByRoleName(String name);
}
