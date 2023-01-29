package fa.training.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SupplierPartnerDto {
	private Long suppliesPartnerId;
	private String remarks;
	private String suppliesPartnerName;
}
