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
@Table(name = "university")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class University {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "university_id")
	private Long id;
	@Column(name = "university_name", unique = true)
	private String name;
	@Column(name = "remarks")
	private String remarks;
	@Column(name = "is_delete")
	private boolean isDelete;

}
