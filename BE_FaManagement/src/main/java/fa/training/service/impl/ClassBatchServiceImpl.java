package fa.training.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fa.training.entity.Audit;
import fa.training.entity.BudgetDetail;
import fa.training.entity.ClassAdmin;
import fa.training.entity.ClassAdminProfile;
import fa.training.entity.ClassBatch;
import fa.training.entity.Trainee;
import fa.training.entity.TraineeProfile;
import fa.training.entity.Trainer;
import fa.training.entity.TrainerProfile;
import fa.training.mapper.AuditMapper;
import fa.training.mapper.BudgetDetailMapper;
import fa.training.mapper.ClassBatchDetailMapper;
import fa.training.mapper.ClassBatchMapper;
import fa.training.model.AuditDto;
import fa.training.model.BudgetDetailDto;
import fa.training.model.ClassBatchDetailDto;
import fa.training.model.ClassBatchDto;
import fa.training.model.ClassRequestInfoDto;
import fa.training.model.TraineeClassDto;
import fa.training.repository.AuditRepository;
import fa.training.repository.BudgetDetailRepository;
import fa.training.repository.ClassAdminProfileRepository;
import fa.training.repository.ClassAdminRepository;
import fa.training.repository.ClassBatchPaginatedRepository;
import fa.training.repository.ClassBatchRepository;
import fa.training.repository.ClassBatchTypeRepository;
import fa.training.repository.ClassTrainerRepository;
import fa.training.repository.LocationRepository;
import fa.training.repository.PositionRepository;
import fa.training.repository.TraineeProfileRepository;
import fa.training.repository.TraineeRepository;
import fa.training.repository.TrainerProfileRepository;
import fa.training.service.ClassBatchService;
import fa.training.util.CLASS_STATUS;
import fa.training.util.DELIVERY_MANAGER_INFO;
import fa.training.util.EmailSender;
import fa.training.util.MESSAGE;
import fa.training.util.MemberLogin;
import fa.training.util.PaginationResult;
import fa.training.util.ROLE;
import fa.training.util.SearchRequest;
import fa.training.util.TRAINEE_ALLOCATION_STATUS;
import fa.training.util.TRAINEE_STATUS;
import fa.training.util.TRAINEE_STATUS_IN_CLASS;
import fa.training.util.TRAINER_TYPE;
import fa.training.util.ValidateData;

@Service
@Transactional(rollbackFor = Exception.class)
public class ClassBatchServiceImpl implements ClassBatchService {

	@Autowired
	private ClassBatchRepository classBatchRepository;

	@Autowired
	private ClassBatchPaginatedRepository classBatchPaginatedRepository;

	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private LocationRepository locationRepository;
	@Autowired
	private ClassBatchTypeRepository classBatchTypeRepository;
	@Autowired
	private PositionRepository positionRepository;
	@Autowired
	private ClassAdminRepository adminClassRepository;
	@Autowired
	private ClassTrainerRepository classTrainerRepository;
	@Autowired
	private BudgetDetailRepository budgetDetailRepository;
	@Autowired
	private AuditRepository auditRepository;
	@Autowired
	private MemberLogin memberLogin;
	@Autowired
	private ClassAdminProfileRepository classAdminRepository;
	@Autowired
	private TrainerProfileRepository trainerProfileRepository;
	@Autowired
	private TraineeProfileRepository traineeProfileRepository;
	@Autowired
	private TraineeRepository traineeRepository;
	@Autowired
	private ValidateData validateData;
	@Autowired
	private EmailSender emailSender;

	@Override
	public List<ClassBatchDto> getAllClassBatch() {
		// TODO Auto-generated method stub
		return ClassBatchMapper.INSTANCE.listEntityToListDto(classBatchRepository.findAll());
	}

	@Override
	public PaginationResult paginatedClassResult(int pageSize, int pageNum) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
		Page<ClassBatch> pageOfClass = classBatchPaginatedRepository.findAll(pageable);
		List<ClassBatchDto> listOfClassDto = ClassBatchMapper.INSTANCE.listEntityToListDto(pageOfClass.getContent());
		PaginationResult result = new PaginationResult();
		result.setAllItems(getAllClassBatch().size());
		result.setCurrentPage(pageNum);
		result.setTotalPages(pageOfClass.getTotalPages());
		result.setTotalItems(pageOfClass.getNumberOfElements());
		result.setListOfObjects(listOfClassDto);
		result.setPageSize(pageSize);
		return result;
	}

	@Override
	public ClassBatchDto getClassById(Long id) {
		return ClassBatchMapper.INSTANCE.entityToDto(classBatchPaginatedRepository.findById(id).get());
	}

//	@Override
//	public List<ClassBatchDto> getListClassBatchBySearch(SearchRequest searchRequest) {
//		List<String> columns;
//
//		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//		CriteriaQuery<ClassBatch> q = cb.createQuery(ClassBatch.class);
//		Root<ClassBatch> classBatchRoot = q.from(ClassBatch.class);
//
//		List<Predicate> predicates = new ArrayList<>();
//		List<ClassBatchDto> resultList = new ArrayList<ClassBatchDto>();
//		int selected = 0;
//		String name = "";
//		columns = searchRequest.getColumns();
//		for (int i = 0; i < columns.size(); i++) {
//			name = columns.get(i).toString();
//			if (searchRequest.getSearchData().getClassId() > 0 && name.equals("classId")) {
//				predicates.add(cb.equal(classBatchRoot.get(String.valueOf(columns.get(i))).as(Long.class),
//						searchRequest.getSearchData().getClassId()));
//				selected = 1;
//				continue;
//			}
//
//			if (searchRequest.getSearchData().getLocationID() > 1 && name.equals("locationID")) {
//				predicates.add(cb.equal(classBatchRoot.get(String.valueOf(columns.get(i))).as(Long.class),
//						searchRequest.getSearchData().getLocationID()));
//				selected = 2;
//				continue;
//			}
//
//			if (!searchRequest.getSearchData().getStatus().equals("All") && name.equals("status")) {
//				predicates.add(cb.like(classBatchRoot.get(String.valueOf(columns.get(i))).as(String.class),
//						"%" + searchRequest.getSearchData().getStatus() + "%"));
//				selected = 3;
//				continue;
//			}
//
//			if (searchRequest.getSearchData().getActualStartDate() != null && name.equals("actualStartDate")) {
//				predicates.add(cb.like(classBatchRoot.get(String.valueOf(columns.get(i))).as(String.class),
//						"%" + searchRequest.getSearchData().getActualStartDate() + "%"));
//				selected = 4;
//				continue;
//			}
//
//			if (searchRequest.getSearchData().getActualEndDate() != null && name.equals("actualEndDate")) {
//				predicates.add(cb.like(classBatchRoot.get(String.valueOf(columns.get(i))).as(String.class),
//						"%" + searchRequest.getSearchData().getActualEndDate() + "%"));
//				selected = 5;
//				continue;
//			}
//		}
//
//		if (selected == 1) {
//			ClassBatchDto result = ClassBatchMapper.INSTANCE.entityToDto(
//					classBatchPaginatedRepository.findById(searchRequest.getSearchData().getClassId()).get());
//			resultList.add(result);
//			return resultList;
//		}
//
//		if (selected != 0) {
//			q.select(classBatchRoot).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
//			resultList = ClassBatchMapper.INSTANCE.listEntityToListDto(entityManager.createQuery(q).getResultList());
//			return resultList;
//		}
//
//		return getAllClassBatch();
//	}

	@Override
	public PaginationResult paginatedClassResultAfterSearch(SearchRequest searchRequest, int pageSize, int pageNum) {
		List<String> columns;

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<ClassBatch> q = cb.createQuery(ClassBatch.class);
		Root<ClassBatch> classBatchRoot = q.from(ClassBatch.class);
		Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
		List<Predicate> predicates = new ArrayList<>();
		List<ClassBatchDto> resultList = new ArrayList<ClassBatchDto>();
		List<ClassBatchDto> resultListPerPage = new ArrayList<ClassBatchDto>();
		int selected = 0;
		String name = "";
		columns = searchRequest.getColumns();
		for (int i = 0; i < columns.size(); i++) {
			name = columns.get(i).toString();
			if (searchRequest.getSearchData().getClassId() > 0 && name.equals("classId")) {
				predicates.add(cb.equal(classBatchRoot.get(String.valueOf(columns.get(i))).as(Long.class),
						searchRequest.getSearchData().getClassId()));
				selected = 1;
				continue;
			}

			if (searchRequest.getSearchData().getLocationID() > 1 && name.equals("locationID")) {
				predicates.add(cb.equal(classBatchRoot.get(String.valueOf(columns.get(i))).as(Long.class),
						searchRequest.getSearchData().getLocationID()));
				selected = 2;
				continue;
			}

			if (!searchRequest.getSearchData().getStatus().equals("All") && name.equals("status")) {
				predicates.add(cb.like(classBatchRoot.get(String.valueOf(columns.get(i))).as(String.class),
						"%" + searchRequest.getSearchData().getStatus() + "%"));
				selected = 3;
				continue;
			}

			if (searchRequest.getSearchData().getActualStartDate() != null && name.equals("actualStartDate")) {
				predicates.add(
						cb.lessThanOrEqualTo(classBatchRoot.get(String.valueOf(columns.get(i))).as(LocalDate.class),
								searchRequest.getSearchData().getActualStartDate()));
				selected = 4;
				continue;
			}

			if (searchRequest.getSearchData().getActualEndDate() != null && name.equals("actualEndDate")) {
				predicates.add(
						cb.greaterThanOrEqualTo(classBatchRoot.get(String.valueOf(columns.get(i))).as(LocalDate.class),
								searchRequest.getSearchData().getActualEndDate()));
				selected = 5;
				continue;
			}
		}

		if (selected == 1) {
			ClassBatchDto result = ClassBatchMapper.INSTANCE.entityToDto(
					classBatchPaginatedRepository.findById(searchRequest.getSearchData().getClassId()).get());
			resultList.add(result);

			Page<ClassBatchDto> page = new PageImpl<>(resultList, pageable, resultList.size());
			PaginationResult pageResult = new PaginationResult();
			pageResult.setCurrentPage(pageNum);
			pageResult.setTotalPages(page.getTotalPages());
			pageResult.setTotalItems(page.getNumberOfElements());
			pageResult.setListOfObjects(page.getContent());
			pageResult.setPageSize(pageSize);
			pageResult.setAllItems(getAllClassBatch().size());
			return pageResult;
		}

		if (selected != 0) {
			q.select(classBatchRoot).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));

			resultList = ClassBatchMapper.INSTANCE.listEntityToListDto(entityManager.createQuery(q).getResultList());
			if (resultList.size() > 0) {
				for (int i = 0; i < pageSize; i++) {
					if ((resultList.size() - 1) < i) {
						break;
					}
					if((i + pageSize * (pageNum - 1)) <= (resultList.size()-1)) {
						resultListPerPage.add(resultList.get(i + pageSize * (pageNum - 1)));
					}		
				}
			}
			Page<ClassBatchDto> page = new PageImpl<>(resultListPerPage, pageable, resultList.size());
			PaginationResult result = new PaginationResult();
			result.setCurrentPage(pageNum);
			result.setTotalPages(page.getTotalPages());
			result.setTotalItems(page.getNumberOfElements());
			result.setListOfObjects(page.getContent());
			result.setPageSize(pageSize);
			result.setAllItems(getAllClassBatch().size());
			return result;
		}
		return paginatedClassResult(pageSize, pageNum);
	}

	@Override
	public void addClass(ClassBatchDetailDto batchDetailDto) {
		// validate budget
		float total = caculateTotalBudget(batchDetailDto.getBudgetDetailDtos());
		if (total > batchDetailDto.getEstimateBudget()) {
			throw new IllegalArgumentException("Over budget");
		}
		// validate start date and end date
		if (!validateData.validateStartDate(batchDetailDto.getExpectedStartDate(), batchDetailDto.getExpectedEndDate()))
			throw new IllegalArgumentException(MESSAGE.MSG7.msg);

		// add class
		ClassBatch classBatch = ClassBatchDetailMapper.INSTANCE.dtoToEntity(batchDetailDto);
		String classCode = generateClassCode(batchDetailDto);
		classBatch.setClassCode(classCode);
		String className = generateClassName(batchDetailDto);
		classBatch.setClassName(className);
		classBatch.setStatus(CLASS_STATUS.DRAFT.status);
		classBatch.setHistory(LocalDate.now() + " Created by " + memberLogin.getUserLogin().getUsername());
		classBatch = classBatchRepository.save(classBatch);

		// add class admin
		List<ClassAdmin> classAdmins = new ArrayList<ClassAdmin>();
		for (Long adminId : batchDetailDto.getClassAdminIds()) {
			ClassAdmin adminClass = new ClassAdmin();
			adminClass.setProfileId(adminId);
			adminClass.setClassId(classBatch.getClassId());
			classAdmins.add(adminClass);
		}
		adminClassRepository.saveAll(classAdmins);

		// add master trainer to class
		Trainer masterTrainer = new Trainer();
		masterTrainer.setClassId(classBatch.getClassId());
		masterTrainer.setTrainerProfileId(batchDetailDto.getMasterTrainerId());
		masterTrainer.setType(TRAINER_TYPE.MASTER_TRAINER.type);
		classTrainerRepository.save(masterTrainer);

		// add trainer to class
		List<Trainer> trainers = new ArrayList<>();
		for (Long trainerId : batchDetailDto.getListOfTrainerId()) {
			if (trainerId != batchDetailDto.getMasterTrainerId()) {
				Trainer trainer = new Trainer();
				trainer.setClassId(classBatch.getClassId());
				trainer.setTrainerProfileId(trainerId);
				trainer.setType(TRAINER_TYPE.TRAINER.type);
				trainers.add(trainer);
			}
		}
		classTrainerRepository.saveAll(trainers);

		// add budget details
		List<BudgetDetail> budgetDetails = BudgetDetailMapper.INSTANCE
				.listDtoToListEntity(batchDetailDto.getBudgetDetailDtos());
		for (BudgetDetail budgetDetail : budgetDetails) {
			if (!validateData.validateBudgetDetail(budgetDetail))
				throw new IllegalArgumentException("Budget validate failed");
			budgetDetail.setClassId(classBatch.getClassId());
		}
		budgetDetailRepository.saveAll(budgetDetails);

		// add audit
		List<Audit> audits = AuditMapper.INSTANCE.listDtoToListEntity(batchDetailDto.getAuditDtos());
		for (Audit audit : audits) {
			if (!validateData.validateAudit(audit))
				throw new IllegalArgumentException("Audit validate failed");
			audit.setClassId(classBatch.getClassId());
		}
		auditRepository.saveAll(audits);

		// send email to class admin
		emailSender.sendEmailToClassAdminET1(batchDetailDto.getClassAdminIds(), classBatch);
	}

	@Override
	public ClassBatchDetailDto getDetailClassById(Long id) {
		ClassBatch classBatch = classBatchRepository.findById(id).get();
		ClassBatchDetailDto classBatchDetailDto = ClassBatchDetailMapper.INSTANCE.entityToDto(classBatch);
		// add admin to class batch detail
		List<ClassAdminProfile> adminProfiles = classAdminRepository.findByClassId(id);
		classBatchDetailDto.setClassAdminIds(new ArrayList<>());
		classBatchDetailDto.setClassAdminNames(new ArrayList<>());
		adminProfiles.stream().forEach(admin -> {
			classBatchDetailDto.getClassAdminIds().add(admin.getId());
			classBatchDetailDto.getClassAdminNames().add(admin.getFullName());
		});
		// add master trainer to class batch detail
		TrainerProfile masterTrainer = trainerProfileRepository.findMasterTrainerByClassId(id);
		if (masterTrainer != null) {
			classBatchDetailDto.setMasterTrainerId(masterTrainer.getTrainerProfileId());
			classBatchDetailDto.setMasterTrainerName(masterTrainer.getFullName());
		}
		// add trainer to class batch detail
		List<TrainerProfile> trainerProfiles = trainerProfileRepository.findTrainerByClassId(id);
		classBatchDetailDto.setListOfTrainerId(new ArrayList<>());
		classBatchDetailDto.setListOfTrainerName(new ArrayList<>());
		trainerProfiles.stream().forEach(trainerProfile -> {
			classBatchDetailDto.getListOfTrainerId().add(trainerProfile.getTrainerProfileId());
			classBatchDetailDto.getListOfTrainerName().add(trainerProfile.getFullName());
		});
		// add budget detail to class batch detail
		List<BudgetDetailDto> budgetDetailDtos = BudgetDetailMapper.INSTANCE
				.listEntityToListDto(budgetDetailRepository.findAllByClassId(id));
		classBatchDetailDto.setBudgetDetailDtos(budgetDetailDtos);
		classBatchDetailDto.setTotalBudget(caculateTotalBudget(budgetDetailDtos));
		// add audit to class batch detail
		List<AuditDto> auditDtos = AuditMapper.INSTANCE.listEntityToListDto(auditRepository.findByClassId(id));
		classBatchDetailDto.setAuditDtos(auditDtos);

		return classBatchDetailDto;
	}

	@Override
	public void updateClassBatch(Long id, ClassBatchDetailDto classBatchDetailDto) {
		// validate budget
		float total = caculateTotalBudget(classBatchDetailDto.getBudgetDetailDtos());
		if (total > classBatchDetailDto.getEstimateBudget()) {
			throw new IllegalArgumentException("Over budget");
		}
		// validate start date and end date
		if (!validateData.validateStartDate(classBatchDetailDto.getExpectedStartDate(),
				classBatchDetailDto.getExpectedEndDate()))
			throw new IllegalArgumentException(MESSAGE.MSG7.msg);
		// validate class status
		ClassBatch classBatch = classBatchRepository.findById(id).get();
		if (!classBatch.getStatus().equals(CLASS_STATUS.DRAFT.status)
				&& !classBatch.getStatus().equals(CLASS_STATUS.IN_PROCESS.status))
			throw new IllegalArgumentException("Wrong status");
		List<Audit> auditsAF = AuditMapper.INSTANCE.listDtoToListEntity(classBatchDetailDto.getAuditDtos());
		if (!auditsAF.containsAll(classBatch.getAudits()) && !classBatch.getAudits().containsAll(auditsAF)
				&& !classBatch.getStatus().equals(CLASS_STATUS.IN_PROCESS.status)) {
			throw new IllegalArgumentException("Wrong status");
		}

		classBatch.setPlannedTraneeNumber(classBatchDetailDto.getPlannedTraneeNumber());
		classBatch.setExpectedStartDate(classBatchDetailDto.getExpectedStartDate());
		classBatch.setExpectedEndDate(classBatchDetailDto.getExpectedEndDate());
		classBatch.setLocationID(classBatchDetailDto.getLocationID());
		classBatch.setEstimateBudget(classBatchDetailDto.getEstimateBudget());
		classBatch.setDetailLocation(classBatchDetailDto.getDetailLocation());
		classBatch.setWeightedNumber(classBatchDetailDto.getWeightedNumber());
		classBatch.setSubjectTypeId(classBatchDetailDto.getSubjectTypeId());
		classBatch.setSubSubjectTypeId(classBatchDetailDto.getSubSubjectTypeId());
		classBatch.setDeliveryTypeId(classBatchDetailDto.getDeliveryTypeId());
		classBatch.setFormatTypeId(classBatchDetailDto.getFormatTypeId());
		classBatch.setScopeId(classBatchDetailDto.getScopeId());
		classBatch.setSupplierOrPartnerId(classBatchDetailDto.getSupplierOrPartnerId());
		classBatch.setCurriculum(classBatchDetailDto.getCurriculum());
		classBatch.setRemarks(classBatchDetailDto.getRemarks());
		String history = LocalDate.now() + " Updated by " + memberLogin.getUserLogin().getUsername();
		classBatch.setHistory(classBatch.getHistory() + ", " + history);

		// add class admin to class
		adminClassRepository.deleteAllByClassId(id);
		List<ClassAdmin> classAdmins = new ArrayList<ClassAdmin>();
		for (Long adminId : classBatchDetailDto.getClassAdminIds()) {
			ClassAdmin adminClass = new ClassAdmin();
			adminClass.setProfileId(adminId);
			adminClass.setClassId(classBatch.getClassId());
			classAdmins.add(adminClass);
		}
		adminClassRepository.saveAll(classAdmins);

		// add master trainer to class
		classTrainerRepository.deleleAllByClassId(id);
		Trainer masterTrainer = new Trainer();
		masterTrainer.setClassId(classBatch.getClassId());
		masterTrainer.setTrainerProfileId(classBatchDetailDto.getMasterTrainerId());
		masterTrainer.setType(TRAINER_TYPE.MASTER_TRAINER.type);
		classTrainerRepository.save(masterTrainer);

		// add trainer to class
		List<Trainer> trainers = new ArrayList<>();
		for (Long trainerId : classBatchDetailDto.getListOfTrainerId()) {
			if (trainerId != classBatchDetailDto.getMasterTrainerId()) {
				Trainer trainer = new Trainer();
				trainer.setClassId(classBatch.getClassId());
				trainer.setTrainerProfileId(trainerId);
				trainer.setType(TRAINER_TYPE.TRAINER.type);
				trainers.add(trainer);
			}
		}
		classTrainerRepository.saveAll(trainers);

		// add budget details
		List<BudgetDetail> budgetDetails = BudgetDetailMapper.INSTANCE
				.listDtoToListEntity(classBatchDetailDto.getBudgetDetailDtos());
		for (BudgetDetail budgetDetail : budgetDetails) {
			if (!validateData.validateBudgetDetail(budgetDetail))
				throw new IllegalArgumentException("Budget validate failed");
			budgetDetail.setClassId(classBatch.getClassId());
		}
		budgetDetailRepository.saveAll(budgetDetails);
		if (budgetDetails.size() == 0)
			budgetDetailRepository.deleteAllByClassId(classBatch.getClassId());
		else {
			budgetDetailRepository.deleteAllNotIn(budgetDetails, classBatch.getClassId());
		}

		if (classBatch.getStatus().equals(CLASS_STATUS.IN_PROCESS.status)) {
			List<Audit> audits = AuditMapper.INSTANCE.listDtoToListEntity(classBatchDetailDto.getAuditDtos());
			for (Audit audit : audits) {
				if (!validateData.validateAudit(audit))
					throw new IllegalArgumentException("Audit validate failed");
				audit.setClassId(classBatch.getClassId());
			}
			auditRepository.saveAll(audits);
			if (audits.size() == 0)
				auditRepository.deleteAllByClassId(classBatch.getClassId());
			else
				auditRepository.deleteAllByNotIn(audits, classBatch.getClassId());
		}

		emailSender.sendEmailToClassAdminET2(classBatchDetailDto.getClassAdminIds(), classBatch);
	}

	@Override
	public void cancelClassBatch(List<Long> listOfClassIds) {
		// TODO Auto-generated method stub
		List<ClassBatch> selectedClasses = new ArrayList<>();
		selectedClasses = classBatchRepository.findAllById(listOfClassIds);
		String history = "";
		for (ClassBatch selectedClass : selectedClasses) {
			System.out.println(selectedClass.getStatus());
			if (!selectedClass.getStatus().equals(CLASS_STATUS.DRAFT.status)
					&& !selectedClass.getStatus().equals(CLASS_STATUS.SUBMITED.status)) {
				throw new IllegalArgumentException("Wrong status!!!");
			}

			String historyLines[] = selectedClass.getHistory().trim().split(",");
			String createdLine[] = historyLines[0].split(" ");
			LocalDate createdTime = LocalDate.parse(createdLine[0].trim());
			selectedClass.setStatus(CLASS_STATUS.CANCELLED.status);
			history = LocalDate.now() + " Cancelled by " + memberLogin.getUserLogin().getUsername();
			selectedClass.setHistory(selectedClass.getHistory() + "," + history);
			if (ChronoUnit.DAYS.between(createdTime, LocalDate.now()) >= 1) {
				selectedClass.setDelete(true);
			}
			classBatchRepository.save(selectedClass);

			List<ClassAdminProfile> adminProfiles = classAdminRepository.findByClassId(selectedClass.getClassId());
			TrainerProfile masterTrainer = trainerProfileRepository
					.findMasterTrainerByClassId(selectedClass.getClassId());
			List<TrainerProfile> trainerProfiles = trainerProfileRepository
					.findTrainerByClassId(selectedClass.getClassId());

			emailSender.sendEmailToClassOperatorsET4(adminProfiles, masterTrainer, trainerProfiles, selectedClass);
		}
	}

	@Override
	public void submitClassBatch(Long classId) {
		ClassBatch selectedClass = classBatchRepository.findById(classId).get();

		String history = "";
		System.out.println(selectedClass.getStatus());
		if (!selectedClass.getStatus().equals(CLASS_STATUS.DRAFT.status)) {
			throw new IllegalArgumentException("Wrong status!!!");
		}

		selectedClass.setStatus(CLASS_STATUS.SUBMITED.status);
		history = LocalDate.now() + " Submitted by " + memberLogin.getUserLogin().getUsername();
		selectedClass.setHistory(selectedClass.getHistory() + "," + history);
		classBatchRepository.save(selectedClass);
		// adminClassRepository.deleteAllByClassId(id);
		// List<ClassAdminProfile> classAdminProfiles =
		// classAdminRepository.findByClassId(classId);
		ClassAdminProfile loginAdmin = classAdminRepository.findByUserId(memberLogin.getUserLogin().getId());

		if (memberLogin.getUserLogin().getRoleName().equals("ROLE_CLASSADMIN")) {
			List<ClassAdmin> admins = adminClassRepository.findByClassId(classId);
			int check = 0;
			if (admins.size() > 0) {
				for (ClassAdmin admin : admins) {
					if (admin.getProfileId() == loginAdmin.getId()) {
						check = 1;
						break;
					}
				}

			}
			if (check != 1) {
				ClassAdmin ad = new ClassAdmin();
				ad.setProfileId(loginAdmin.getId());
				ad.setClassId(classId);
				admins.add(ad);
				adminClassRepository.saveAll(admins);
			}
		}

		emailSender.sendEmailToDeliveryManagersET5(selectedClass);
	}

	@Override
	public void approveSubmittedClassBatch(Long classId) {
		// TODO Auto-generated method stub
		ClassBatch selectedClass = classBatchRepository.findById(classId).get();

		String history = "";
		System.out.println(selectedClass.getStatus());
		if (!selectedClass.getStatus().equals(CLASS_STATUS.SUBMITED.status)) {
			throw new IllegalArgumentException("Wrong status!!!");
		}

		selectedClass.setStatus(CLASS_STATUS.PLANNING.status);
		history = LocalDate.now() + " Approved by " + memberLogin.getUserLogin().getUsername();
		selectedClass.setHistory(selectedClass.getHistory() + "," + history);
		classBatchRepository.save(selectedClass);
		emailSender.sendEmailToFAManagerET6(selectedClass);
	}

	@Override
	public void rejectSubmittedClassBatch(ClassBatchDto classInfo) {
		// TODO Auto-generated method stub
		ClassBatch selectedClass = classBatchRepository.findById(classInfo.getClassId()).get();

		String history = "";
		System.out.println(selectedClass.getStatus());
		if (!selectedClass.getStatus().equals(CLASS_STATUS.SUBMITED.status)) {
			throw new IllegalArgumentException("Wrong status!!!");
		}

		selectedClass.setStatus(CLASS_STATUS.REJECTED.status);
		history = LocalDate.now() + " Rejected by " + memberLogin.getUserLogin().getUsername() + " " + classInfo.getRemarks() ;
		selectedClass.setHistory(selectedClass.getHistory() + "," + history);
		classBatchRepository.save(selectedClass);

		List<ClassAdminProfile> adminProfiles = classAdminRepository.findByClassId(selectedClass.getClassId());
		TrainerProfile masterTrainer = trainerProfileRepository
				.findMasterTrainerByClassId(selectedClass.getClassId());
		List<TrainerProfile> trainerProfiles = trainerProfileRepository
				.findTrainerByClassId(selectedClass.getClassId());
		
		emailSender.sendEmailToClassOperatorsET7(adminProfiles, masterTrainer, trainerProfiles, selectedClass);
	}

	@Override
	public void requestForMoreInfo(ClassRequestInfoDto requestInfoDto) {
		ClassBatch classBatch = classBatchRepository.findById(requestInfoDto.getClassId()).get();
		if (!classBatch.getStatus().equals(CLASS_STATUS.PENDING_FOR_REVIEW.status))
			throw new IllegalArgumentException("Wrong status!!!");
		classBatch.setStatus(CLASS_STATUS.WAITING_FOR_MORE_INFO.status);
		String history = LocalDate.now() + " Requested by " + memberLogin.getUserLogin().getUsername() + " - "
				+ requestInfoDto.getRemarks();
		classBatch.setHistory(classBatch.getHistory() + " ," + history);
		classBatch.setRemarks(requestInfoDto.getRemarks());
		classBatchRepository.save(classBatch);
		List<ClassAdminProfile> adminProfiles = classAdminRepository.findByClassId(classBatch.getClassId());
		emailSender.sendEmailToClassAdminET13(adminProfiles, classBatch);

	}

	@Override
	public void removeTraineeFromClass(TraineeClassDto traineeClassDto) {
		ClassBatch classBatch = classBatchRepository.findById(traineeClassDto.getClassId()).get();
		List<TraineeProfile> traineeProfiles = traineeProfileRepository.findAllByIdIn(traineeClassDto.getTraineeIds());
		List<Trainee> trainees = traineeRepository.findAllByClassIdAndTraineeCandidateProfileIdIn(
				classBatch.getClassId(), traineeClassDto.getTraineeIds());
		// validate status
		if (!classBatch.getStatus().equals(CLASS_STATUS.DRAFT.status)) {
			throw new IllegalArgumentException("Wrong class batch status");
		}
		if (traineeProfiles.size() != trainees.size()) {
			throw new IllegalArgumentException("Some trainee are not in class");
		}
		for (TraineeProfile traineeProfle : traineeProfiles) {
			if (!traineeProfle.getStatus().equals(TRAINEE_STATUS.WAITING_FOR_CLASS.status))
				throw new IllegalArgumentException("Wrong trainee status");
			if (!traineeProfle.getAllocationStatus().equals(TRAINEE_ALLOCATION_STATUS.NOT_ALLOCATIED.status))
				throw new IllegalArgumentException("Wrong trainee allocation status");
		}
		for (Trainee trainee : trainees) {
			if (trainee.getStatus().getStatusName().equals(TRAINEE_STATUS_IN_CLASS.ACTIVE.status)) {
				throw new IllegalArgumentException("Wrong trainee in class status");
			}
		}

		traineeRepository.deleteAll(trainees);

	}

	private String generateClassCode(ClassBatchDetailDto batchDetailDto) {
		String locationCode = locationRepository.findById(batchDetailDto.getLocationID()).get().getLocationCode()
				.toUpperCase();
		String classType = classBatchTypeRepository.findById(batchDetailDto.getClassTypeId()).get().getName();
		if (classType.equals("Fresher")) {
			classType = "FR";
		} else
			classType = "CP";
		String skill = batchDetailDto.getSkill().toUpperCase();
		String year = String.valueOf(batchDetailDto.getExpectedStartDate().getYear()).substring(2, 4);
		String serialNumber = String
				.valueOf(classBatchRepository.getCountOfSkillOfYear(batchDetailDto.getExpectedStartDate(),
						batchDetailDto.getSkill(), batchDetailDto.getLocationID()) + 1);
		if (serialNumber.length() < 2)
			serialNumber = "0" + serialNumber;
		return locationCode + "_" + classType + "_" + skill + "_" + year + "_" + serialNumber;
	}

	private String generateClassName(ClassBatchDetailDto batchDetailDto) {
		String position = positionRepository.findById(batchDetailDto.getPositionId()).get().getName();
		String skill = batchDetailDto.getSkill();
		return "Fresher " + position + " " + skill;
	}

	private Float caculateTotalBudget(List<BudgetDetailDto> budgetDetailDtos) {
		Float total = (float) 0;
		for (BudgetDetail budgetDetail : BudgetDetailMapper.INSTANCE.listDtoToListEntity(budgetDetailDtos)) {
			if (validateData.validateBudgetDetail(budgetDetail)) {
				total += budgetDetail.getSum();
			}
		}
		return total;
	}

	@Override
	public void finishClass(List<Long> classIds) {

		List<ClassBatch> selectClass = new ArrayList<>();
		selectClass = classBatchRepository.findAllById(classIds);
		for (ClassBatch classBatch : selectClass) {
			if (!classBatch.getStatus().equals(CLASS_STATUS.IN_PROCESS.status)) {
				throw new IllegalArgumentException("Wrong class batch status");
			}
			String history = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a"))
					+ "_Finished by " + memberLogin.getUserLogin().getUsername();
			classBatch.setStatus(CLASS_STATUS.PENDING_FOR_REVIEW.status);
			classBatch.setHistory(classBatch.getHistory() + ", " + history);
			classBatchRepository.save(classBatch);
			emailSender.sendEmailToDeliveryManagerET11(DELIVERY_MANAGER_INFO.EMAIL.info,DELIVERY_MANAGER_INFO.FULLNAME.info, classBatch);
		}

	}

	@Override
	public boolean changeStatus(String originStatus, String newStatus, Long classId) {
		try {
			ClassBatch classBatch = classBatchRepository.findByIsDeleteAndClassId(false, classId).get();
			if (classBatch != null && classBatch.getStatus().equals(originStatus)) {
				classBatch.setStatus(newStatus);
				List<ClassAdminProfile> classAdminProfiles = classAdminRepository.findByClassId(classId);
				TrainerProfile masterTrainer = trainerProfileRepository.findMasterTrainerByClassId(classId);
				List<TrainerProfile> trainers = trainerProfileRepository.findTrainerByClassId(classId);
				if (newStatus.equals(CLASS_STATUS.PLANNED.get())) {
					classBatch.setHistory(classBatch.getHistory() + ". " + new Date() + "- Approved by "
							+ memberLogin.getUserLogin().getUsername());
					emailSender.sendEmailChangeStatusClass(classBatch, classAdminProfiles, masterTrainer, trainers,
							" has been approved.", "ET8_template.html");
				} else if (newStatus.equals(CLASS_STATUS.IN_PROCESS.get())) {
					classBatch.setHistory(classBatch.getHistory() + ". " + new Date() + "- Started by "
							+ memberLogin.getUserLogin().getUsername());
					emailSender.sendEmailChangeStatusClass(classBatch, classAdminProfiles, masterTrainer, trainers,
							" has been started.", "ET10_template.html");
				}
				classBatchRepository.save(classBatch);
				return true;
			}
			return false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean changeStatusAndRemarks(String originStatus, String newStatus, Long classId, String remarks) {
		try {
			ClassBatch classBatch = classBatchRepository.findByIsDeleteAndClassId(false, classId).get();
			if (classBatch != null && classBatch.getStatus().equals(originStatus)) {
				classBatch.setStatus(newStatus);
				classBatch.setHistory(classBatch.getHistory() + ". " + new Date() + "- Declined by "
						+ memberLogin.getUserLogin().getUsername() + "- " + remarks);
				classBatchRepository.save(classBatch);
				List<ClassAdminProfile> classAdminProfiles = classAdminRepository.findByClassId(classId);
				TrainerProfile masterTrainer = trainerProfileRepository.findMasterTrainerByClassId(classId);
				List<TrainerProfile> trainers = trainerProfileRepository.findTrainerByClassId(classId);
				emailSender.sendEmailChangeStatusClass(classBatch, classAdminProfiles, masterTrainer, trainers,
						" has been declined.", "ET9_template.html");
				return true;
			}
			return false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public void closeClassBatch(List<Long> classIds) {
		List<ClassBatch> classBatchs = new ArrayList<>();
		classBatchs = classBatchRepository.findAllById(classIds);
		for (ClassBatch classBatch : classBatchs) {
			if (!classBatch.getStatus().equals(CLASS_STATUS.PENDING_FOR_REVIEW.status)) {
				throw new IllegalArgumentException("Wrong class batch status");
			}
			String history = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a"))
					+ "_Closed by " + memberLogin.getUserLogin().getUsername();
			classBatch.setStatus(CLASS_STATUS.CLOSED.status);
			classBatch.setHistory(classBatch.getHistory() + ", " + history);
			classBatchRepository.save(classBatch);
			try {
				List<TraineeProfile> managers = traineeProfileRepository.findByRole(ROLE.ROLE_MANAGER.name);
				emailSender.sendEmailToManager(managers, classBatch);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
