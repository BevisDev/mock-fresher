package fa.training.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="allocation")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Allocation {

	@Id
	@GeneratedValue
	private Long id;
	@Column(name="allocated_fsu")
	private String allocatedFSU;
	private Float salary;
	@Column(name="start_date")
	private LocalDate startDate;
	private String status;
	private String remarks;
}
