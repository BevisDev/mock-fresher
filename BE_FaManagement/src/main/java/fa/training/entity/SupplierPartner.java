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
@Table(name = "supplies_partner")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SupplierPartner {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "supplies_partner_id")
	private Long suppliesPartnerId;
	@Column(name = "remarks")
	private String remarks;
	@Column(name = "supplies_partner_name")
	private String suppliesPartnerName;
}