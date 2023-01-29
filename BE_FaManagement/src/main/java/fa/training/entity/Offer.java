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
@Table(name = "offer")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Offer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "offer_id")
	private Long id;
	@Column(name = "job_rank")
	private String jobRank;
	@Column(name = "technology")
	private String technology;
	@Column(name = "contract_type")
	private String contractType;
	@Column(name = "offer_salary")
	private Double offerSalary;
	private String remarks;
}
