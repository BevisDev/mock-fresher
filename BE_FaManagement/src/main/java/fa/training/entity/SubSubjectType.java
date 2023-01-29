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
@Table(name = "sub_subject_type")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SubSubjectType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sub_subject_type_id")
	private Long subSubjectTypeId;
	@Column(name = "remarks")
	private String remarks;
	@Column(name = "sub_subject_type_name")
	private String subSubjectTypeName;
}
