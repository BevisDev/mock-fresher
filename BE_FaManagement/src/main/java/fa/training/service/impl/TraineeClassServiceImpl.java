package fa.training.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import fa.training.entity.ClassBatch;
import fa.training.entity.Faculty;
import fa.training.entity.Status;
import fa.training.entity.Trainee;
import fa.training.entity.TraineeProfile;
import fa.training.entity.University;
import fa.training.model.TraineeClassDto;
import fa.training.repository.ClassBatchRepository;
import fa.training.repository.FacultyRepository;
import fa.training.repository.StatusRepository;
import fa.training.repository.TraineeProfileRepository;
import fa.training.repository.TraineeRepository;
import fa.training.repository.UniversityRepository;
import fa.training.service.TraineeClassService;
import fa.training.util.CLASS_STATUS;
import fa.training.util.ExcelProvider;
import fa.training.util.MESSAGE;
import fa.training.util.TRAINEE_ALLOCATION_STATUS;
import fa.training.util.TRAINEE_EXCEL_HEADER;
import fa.training.util.TRAINEE_STATUS;
import fa.training.util.TRAINEE_STATUS_IN_CLASS;
import fa.training.util.TRAINEE_TYPE;
import fa.training.util.ValidateData;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class TraineeClassServiceImpl implements TraineeClassService {
	@Autowired
	private TraineeProfileRepository traineeProfileRepository;
	@Autowired
	private TraineeRepository traineeRepository;
	@Autowired
	private ClassBatchRepository classBatchRepository;
	@Autowired
	private StatusRepository statusRepository;
	@Autowired
	private UniversityRepository universityRepository;
	@Autowired
	private FacultyRepository facultyRepository;
	@Autowired
	private ValidateData validateData;

	@Override
	public void addTraineeToClass(TraineeClassDto traineeClassDto) {
		ClassBatch classBatch = classBatchRepository.findById(traineeClassDto.getClassId()).get();
		List<Trainee> trainees = traineeRepository
				.findAllByTraineeCandidateProfileIdIn(traineeClassDto.getTraineeIds());
		List<TraineeProfile> traineeProfiles = traineeProfileRepository.findAllByIdIn(traineeClassDto.getTraineeIds());

		// validate class batch status
		if (!classBatch.getStatus().equals(CLASS_STATUS.DRAFT.status)) {
			throw new IllegalArgumentException("Wrong class batch status");
		}

		for (TraineeProfile traineeProfile : traineeProfiles) {
			if (!traineeProfile.getStatus().equals(TRAINEE_STATUS.WAITING_FOR_CLASS.status)
					&& !traineeProfile.getStatus().equals(TRAINEE_STATUS.WAITING_FOR_ALLOCATION.status)
					&& !traineeProfile.getStatus().equals(TRAINEE_STATUS.DEFERRED.status)
					&& !traineeProfile.getStatus().equals(TRAINEE_STATUS.DROP_OUT.status)) {
				throw new IllegalArgumentException("Wrong trainee profile status");
			}
		}

		for (Trainee trainee : trainees) {
			if (trainee.getStatus().getStatusName().equals(TRAINEE_STATUS_IN_CLASS.ENROLLED.status)
					|| trainee.getStatus().getStatusName().equals(TRAINEE_STATUS_IN_CLASS.IN_PROGRESS.status)) {
				throw new IllegalArgumentException("Wrong trainee status in class");
			}
		}
		List<Trainee> traineesAdd = new ArrayList<Trainee>();
		for (Long traineeProfileId : traineeClassDto.getTraineeIds()) {
			Trainee trainee = new Trainee();
			trainee.setClassId(traineeClassDto.getClassId());
			trainee.setTraineeCandidateProfileId(traineeProfileId);
			Status status = statusRepository.findByStatusName(TRAINEE_STATUS_IN_CLASS.ENROLLED.status);
			trainee.setStatusId(status.getStatusId());
			traineesAdd.add(trainee);
		}
		traineeRepository.saveAll(traineesAdd);
		for (TraineeProfile traineeProfile : traineeProfiles) {
			traineeProfile.setStatus(TRAINEE_STATUS.ENROLLED.status);
			traineeProfile.setAllocationStatus(TRAINEE_ALLOCATION_STATUS.NOT_ALLOCATIED.status);
		}
		traineeProfileRepository.saveAll(traineeProfiles);
	}

	@Override
	public boolean importTraineeToClass(Long classId, MultipartFile file) {
		boolean rs = false;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		List<TraineeProfile> traineeProfiles = new ArrayList<>();
		Path tempFile;
		try {
			tempFile = Files.createTempFile(null, null);
			Files.write(tempFile, file.getBytes());
			File fileToRead = tempFile.toFile();
			FileInputStream fileToImport = new FileInputStream(fileToRead);
			Workbook workbook = new XSSFWorkbook(fileToImport);
			Sheet sheet = workbook.getSheetAt(0);

			// get all row
			Iterator<Row> iterator = sheet.iterator();
			while (iterator.hasNext()) {
				Row nextRow = iterator.next();
				if (nextRow.getRowNum() == 0) {
					// Ignore header
					continue;
				}

				// Get all cells
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				TraineeProfile traineeProfile = new TraineeProfile();
				while (cellIterator.hasNext()) {
					// Read cell
					Cell cell = cellIterator.next();
					Object cellValue = ExcelProvider.getCellValue(cell);
					if (cellValue == null || cellValue.toString().isEmpty()) {
						continue;
					}
					int columnIndex = cell.getColumnIndex();
					switch (columnIndex) {
					case TRAINEE_EXCEL_HEADER.NAME:
						traineeProfile.setFullName(cellValue.toString());
						;
						break;
					case TRAINEE_EXCEL_HEADER.DOB:
						traineeProfile.setDateOfBirth(LocalDate.parse(cellValue.toString(), formatter));
						break;
					case TRAINEE_EXCEL_HEADER.GENDER:
						traineeProfile.setGender(cellValue.toString());
						break;
					case TRAINEE_EXCEL_HEADER.UNIVERSITY:
						University university = universityRepository.findByName(cellValue.toString());
						if (university == null) {
							university = new University();
							university.setName(cellValue.toString().toString());
							university.setDelete(false);
							university = universityRepository.save(university);
						}
						traineeProfile.setUniversityId(university.getId());
						break;
					case TRAINEE_EXCEL_HEADER.FACULTY:
						Faculty faculty = facultyRepository.findByName(cellValue.toString());
						if (faculty == null) {
							faculty = new Faculty();
							faculty.setName(cellValue.toString());
							faculty.setDelete(false);
							faculty = facultyRepository.save(faculty);
						}
						traineeProfile.setFacultyId(faculty.getId());
						break;
					case TRAINEE_EXCEL_HEADER.PHONE:
						if(!validateData.validatePhone(cellValue.toString()).equals(""))
							throw new IllegalArgumentException(validateData.validatePhone(cellValue.toString()));
						traineeProfile.setPhone(cellValue.toString());
						break;
					case TRAINEE_EXCEL_HEADER.EMAIL:
						if(!validateData.validateEmail(cellValue.toString()))
							throw new IllegalArgumentException(MESSAGE.MSG5.msg);
						traineeProfile.setEmail(cellValue.toString());
						break;
					default:
						break;
					}
				}
				TraineeProfile traineeProfileImport = traineeProfileRepository
						.findByFullNameAndDateOfBirthAndGenderAndUniversityIdAndFacultyIdAndPhoneAndEmail(
								traineeProfile.getFullName(), traineeProfile.getDateOfBirth(),
								traineeProfile.getGender(), traineeProfile.getUniversityId(),
								traineeProfile.getFacultyId(), traineeProfile.getPhone(), traineeProfile.getEmail());
				if (traineeProfileImport == null) {
					traineeProfile.setStatus(TRAINEE_STATUS.WAITING_FOR_CLASS.status);
					traineeProfile.setAllocationStatus(TRAINEE_ALLOCATION_STATUS.NOT_ALLOCATIED.status);
					traineeProfile.setAccount(generateAccount(traineeProfile.getFullName()));
					traineeProfile.setType(TRAINEE_TYPE.TRAINEE.type);
					traineeProfile.setIsDelete(false);
					traineeProfileImport = traineeProfileRepository.save(traineeProfile);
				}
				traineeProfiles.add(traineeProfileImport);

			}
			workbook.close();
		} catch (IOException e) {
			log.error("Import trainee failed");
		}
		if (traineeProfiles.size() > 0) {
			TraineeClassDto traineeClassDto = new TraineeClassDto();
			traineeClassDto.setClassId(classId);
			List<Long> traineeProfileIDs = new ArrayList<Long>();
			for (TraineeProfile traineeProfile : traineeProfiles) {
				Trainee trainee = traineeRepository.findByClassIdAndTraineeCandidateProfileId(classId,
						traineeProfile.getId());
				if (trainee == null) {
					traineeProfileIDs.add(traineeProfile.getId());
				}
			}
			traineeClassDto.setTraineeIds(traineeProfileIDs);
			addTraineeToClass(traineeClassDto);
			rs = true;
		}

		return rs;
	}

	public String generateAccount(String name) {
		String[] arrName = name.trim().split(" ");
		StringBuilder result = new StringBuilder();
		result.append(arrName[arrName.length - 1]);
		for (int i = 0; i < arrName.length - 1; i++) {
			result.append(arrName[i].toUpperCase().charAt(0));
		}
		List<TraineeProfile> traineeProfiles = traineeProfileRepository.getTraineeProfileByAccount(result.toString());
		int count = 0;
		for (TraineeProfile traineeProfile : traineeProfiles) {
			if (result.toString().equals(traineeProfile.getAccount().replaceAll("\\d", ""))) {
				count++;
			}
		}
		if (count != 0) {
			result.append(count);
		}
		return result.toString();
	}
}
