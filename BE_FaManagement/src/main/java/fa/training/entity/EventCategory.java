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
@Table(name="event_category")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EventCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="event_category_id")
	private Long id;
	@Column(name="event_category_name")
	private String name;
}
