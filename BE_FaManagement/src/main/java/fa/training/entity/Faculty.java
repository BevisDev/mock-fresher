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
@Table(name = "faculty")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Faculty {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "faculty_id")
	private Long id;
	@Column(name = "faculty_name",unique = true)
	private String name;
	private String remarks;
	@Column(name = "is_delete")
	private boolean isDelete;
}
