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
@Table(name = "subject_type")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SubjectType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "subject_type_id")
	private Long subjectTypeId;
	@Column(name = "remarks")
	private String remarks;
	@Column(name = "subject_type_name")
	private String subjectTypeName;
}
