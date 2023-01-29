package fa.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fa.training.entity.Channel;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {
	public List<Channel> findByIsDelete(boolean isDelete);
}
