package fa.training.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "channel")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Channel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "channel_id")
	private Long id;
	@Column(name = "channel_name")
	private String name;
	@Column(name = "remarks")
	private String remarks;
	@Column(name = "is_delete")
	private boolean isDelete;
}
