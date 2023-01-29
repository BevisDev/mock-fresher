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
@Table(name = "location")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Location {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "location_id")
	private Long locationId;
	@Column(name = "remarks")
	private String remarks;
	@Column(name = "location_name")
	private String locationName;
	@Column(name="location_code")
	private String locationCode;
	@Column(name = "is_delete")
	private boolean isDelete;
}
