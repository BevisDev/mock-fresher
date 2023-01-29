package fa.training.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "class_batch")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ClassBatch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "class_id")
	private Long classId;
	@Column(name = "class_name", columnDefinition = "varchar(255)")
	private String className;
	@Column(name = "class_code", unique = true)
	private String classCode;
	@Column(name = "expect_start_date")
	private LocalDate expectedStartDate;
	@Column(name = "expect_end_date")
	private LocalDate expectedEndDate;
	@Column(name = "location_id")
	private Long locationID;
	@Column(name = "detail_location")
	private String detailLocation;
	@Column(name = "planned_trainee_number", columnDefinition = "numeric(19,0)")
	private BigDecimal plannedTraneeNumber;
	@Column(name = "budget_id")
	private Long budgetId;
	@Column(name = "estimate_budget")
	private float estimateBudget;
	@Column(name = "sub_subject_type_id")
	private Long subSubjectTypeId;
	@Column(name = "delivery_type_id")
	private Long deliveryTypeId;
	@Column(name = "format_type_id")
	private Long formatTypeId;
	@Column(name = "scope_id")
	private Long scopeId;
	@Column(name = "weighted_number")
	private String WeightedNumber;
	@Column(name = "supplier_or_partner_id")
	private Long supplierOrPartnerId;
	@Column(name = "actual_start_date")
	private LocalDate actualStartDate;
	@Column(name = "actuial_end_date")
	private LocalDate actualEndDate;
	@Column(name = "accepted_trainee_number", columnDefinition = "numeric(19,0)")
	private BigDecimal acceptedTraineeNumber;
	@Column(name = "actual_trainee_number", columnDefinition = "numeric(19,0)")
	private BigDecimal actualTraneeNumber;
	@Column(name = "trainer_id")
	private Long trainerId;
	@Column(name = "milestones")
	private LocalDate milestones;
	@Column(name = "curriculum")
	private String curriculum;
	@Column(name = "trainee_or_candidate_id")
	private Long traineeOrCandidateId;
	@Column(name = "subject_type_id")
	private Long subjectTypeId;
	@Column(name = "status")
	private String status;
	@Column(name = "remarks")
	private String remarks;
	@Column(name = "is_delete")
	private boolean isDelete;
	@Column(name = "history")
	private String history;
	@Column(name = "skill")
	private String skill;
	@Column(name = "class_type_id")
	private Long classTypeId;
	@Column(name = "position_id")
	private Long positionId;

	@ManyToOne
	@JoinColumn(name = "supplier_or_partner_id", insertable = false, updatable = false)
	private SupplierPartner suppliesPartner;
	@ManyToOne
	@JoinColumn(name = "scope_id", insertable = false, updatable = false)
	private Scope scope;
	@ManyToOne
	@JoinColumn(name = "format_type_id", insertable = false, updatable = false)
	private FormatType formatType;
	@ManyToOne
	@JoinColumn(name = "delivery_type_id", insertable = false, updatable = false)
	private DeliveryType deliveryType;
	@ManyToOne
	@JoinColumn(name = "sub_subject_type_id", insertable = false, updatable = false)
	private SubSubjectType subSubjectType;
	@ManyToOne
	@JoinColumn(name = "subject_type_id", insertable = false, updatable = false)
	private SubjectType subjectType;
	@ManyToOne
	@JoinColumn(name = "location_id", insertable = false, updatable = false)
	private Location location;
	@ManyToOne
	@JoinColumn(name = "budget_id", insertable = false, updatable = false)
	private Budget budget;
	@ManyToOne
	@JoinColumn(name = "class_type_id", insertable = false, updatable = false)
	private ClassBatchType classBatchType;
	@ManyToOne
	@JoinColumn(name = "position_id", insertable = false, updatable = false)
	private Position position;
	@OneToMany(mappedBy = "classBatch")
	private List<Audit> audits;
	
}
