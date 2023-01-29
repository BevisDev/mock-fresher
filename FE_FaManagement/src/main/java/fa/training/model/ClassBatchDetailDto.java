package fa.training.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ClassBatchDetailDto {

	private Long classId;
	private String className;
	private String classCode;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate expectedStartDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate expectedEndDate;
	private Long locationID;
	private String detailLocation;
	private BigDecimal plannedTraneeNumber;
	private Long budgetId;
	private float estimateBudget;
	private Long subSubjectTypeId;
	private Long deliveryTypeId;
	private Long formatTypeId;
	private Long scopeId;
	private Long supplierOrPartnerId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate actualStartDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate actualEndDate;
	private BigDecimal acceptedTraineeNumber;
	private BigDecimal actualTraneeNumber;
	private Long trainerId;
	private LocalDate milestones;
	private String curriculum;
	private Long traineeOrCandidateId;
	private Long subjectTypeId;
	private String status;
	private String remarks;
	private String suppliesPartnerName;
	private String scopeName;
	private String formatTypeName;
	private String deliveryTypeName;
	private String subSubjectTypeName;
	private String subjectTypeName;
	private String locationName;
	private String budgetCode;
	private Long masterTrainerId;
	private String masterTrainerName;
	private List<Long> classAdminIds;
	private List<String> classAdminNames;
	private List<Long> listOfTrainerId;
	private List<String> listOfTrainerName;
	private List<BudgetDetailDto> budgetDetailDtos;
	private List<AuditDto> auditDtos;
	private String weightedNumber;
	private String skill;
	private Long classTypeId;
	private String classBatchTypeName;
	private Long positionId;
	private String positionName;
	private String history;
	private Float totalBudget;

}
