package fa.training.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="role")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Role {
	
	@Id
	private String name;
	@Column(nullable = false)
	private String description;
}
