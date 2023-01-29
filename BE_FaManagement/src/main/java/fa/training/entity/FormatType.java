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
@Table(name = "format_type")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FormatType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "format_type_id")
	private Long formatTypeId;
	@Column(name="format_type_name")
	private String formatTypeName;
	@Column(name = "remarks")
	private String remarks;
}