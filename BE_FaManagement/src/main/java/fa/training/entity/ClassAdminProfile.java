package fa.training.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "class_admin_profile")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClassAdminProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "class_admin_profile_id")
	private Long id;
	@Column(name = "full_name")
	private String fullName;
	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;
	@Column(name = "gender")
	private String gender;
	@Column(name = "phone",unique = true)
	private String phone;
	@Column(name = "email",unique = true)
	private String email;
	@Column(name="remarks")
	private String remarks;
	@Column(name = "user_id")
	private Long userId;
	@Column(name="is_delete")
	private boolean isDelete = false;

	@OneToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private User user;
}
