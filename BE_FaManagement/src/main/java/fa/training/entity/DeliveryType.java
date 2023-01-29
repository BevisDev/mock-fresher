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
@Table(name = "delivery_type")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DeliveryType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "delivery_type_id")
	private Long id;
	@Column(name="delivery_type_name")
	private String name;
	@Column(name = "remarks")
	private String remarks;
}
