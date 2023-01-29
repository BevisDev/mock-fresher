package fa.training.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fa.training.model.CandidateForm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "candidate")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Candidate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "candidate_id")
	private Long id;
	@Column(name = "trainee_profile_id")
	private Long profileId;
	@Column(name = "application_date")
	private LocalDate applicationDate;
	@Column(name = "channel_id")
	private Long channelId;
	@Column(name = "location_id")
	private Long locationId;
	@Column(name = "offer_id")
	private Long offerId;
	@Column(name = "status")
	private String status;
	@Column(name = "remarks")
	private String remarks;
	@Column(name = "is_delete")
	private boolean isDelete;
	@Column(name = "history")
	private String history;
	@ManyToOne
	@JoinColumn(name = "location_id", insertable = false, updatable = false)
	private Location location;
	@ManyToOne
	@JoinColumn(name = "offer_id", insertable = false, updatable = false)
	private Offer offer;
	@ManyToOne
	@JoinColumn(name = "channel_id", insertable = false, updatable = false)
	private Channel channel;

	public Candidate(CandidateForm candidateForm, Long profileId) {
		super();
		this.profileId = profileId;
		this.applicationDate = candidateForm.getApplicationDate();
		this.channelId = candidateForm.getChannelId();
		this.locationId = candidateForm.getLocationId();
		this.remarks = candidateForm.getRemarks();
	}

	public void updateCanidate(CandidateForm candidateForm) {
		applicationDate = candidateForm.getApplicationDate();
		channelId = candidateForm.getChannelId();
		locationId = candidateForm.getLocationId();
		remarks = candidateForm.getRemarks();
	}
}
