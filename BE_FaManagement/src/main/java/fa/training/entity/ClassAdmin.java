package fa.training.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "class_admin")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClassAdmin {

	@Id
	@Column(name = "class_admin_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long classAdminId;
	@Column(name = "class_id")
	private Long classId;
	@Column(name = "class_admin_profile_id")
	private Long profileId;
	private String remarks;

	@ManyToOne
	@JoinColumn(name="class_id",insertable = false,updatable = false)
	private ClassBatch classBatch;
	@ManyToOne
	@JoinColumn(name="class_admin_profile_id",insertable = false,updatable = false)
	private ClassAdminProfile adminProfile;
}
