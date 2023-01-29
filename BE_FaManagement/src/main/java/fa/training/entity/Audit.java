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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "audit")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Audit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "audit_id")
	private Long auditId;
	@Column(name = "class_id")
	private Long classId;
	@Column(name = "date")
	private LocalDate date;
	@Column(name = "related_party_people")
	private String relatedPartyOrPeople;
	@Column(name = "action")
	private String action;
	@Column(name = "pic")
	private String pic;
	@Column(name = "deadline")
	private LocalDate deadline;
	@Column(name = "note")
	private String note;
	@Column(name = "event_category_id")
	private Long eventCategoryId;

	@ManyToOne
	@JoinColumn(name = "class_id", insertable = false, updatable = false)
	private ClassBatch classBatch;
	@ManyToOne
	@JoinColumn(name = "event_category_id", insertable = false, updatable = false)
	private EventCategory eventCategory;

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		Audit audit = (Audit) obj;
		return (this.getAuditId() == audit.getAuditId() && this.getDate().equals(audit.getDate())
				&& this.getEventCategoryId() == audit.getEventCategoryId()
				&& this.getRelatedPartyOrPeople().equals(audit.getRelatedPartyOrPeople())
				&& this.getAction().equals(audit.getAction()) && this.getPic().equals(audit.getPic())
				&& this.getDeadline().equals(audit.getDeadline()) && this.getNote().equals(audit.getNote()));
	}
}
