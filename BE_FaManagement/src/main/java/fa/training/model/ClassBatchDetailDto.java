package fa.training.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

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
	@NotNull(message = "Expected start date must not be empty")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate expectedStartDate;
	@NotNull(message = "Expected end date must not be empty")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate expectedEndDate;
	@NotNull(message ="Location must not be empty!")
	private Long locationID;
	private String detailLocation;
	private BigDecimal plannedTraneeNumber;
	@NotNull(message ="Budget code must not be empty!")
	private Long budgetId;
	@NotNull(message ="Estimate budget code must not be empty!")
	@Min(value=0, message="Estimate budget must be a positive number" )
	private float estimateBudget;
	private Long subSubjectTypeId;
	private Long deliveryTypeId;
	private Long formatTypeId;
	private Long scopeId;
	private Long supplierOrPartnerId;
	private LocalDate actualStartDate;
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
	private List<Long> listOfTrainerId;
	private List<String> listOfTrainerName;
	private List<BudgetDetailDto> budgetDetailDtos;
	private List<AuditDto> auditDtos;
	private List<Long> classAdminIds;
	private List<String> classAdminNames;
	@Pattern(regexp = "^$|^\\d{2}-\\d{2}-\\d{2}-\\d{2}$",message = "Wrong format.")
	private String weightedNumber;
	private String skill;
	private Long classTypeId;
	private String classBatchTypeName;
	private Long positionId;
	private String positionName;
	private String history;
	private Float totalBudget;
}
