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
@Table(name = "scope")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Scope {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "scope_id")
	private Long scopeId;
	@Column(name="scope_name")
	private String scopeName;
	@Column(name = "remarks")
	private String remarks;
}
