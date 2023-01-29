package fa.training.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fa.training.entity.Candidate;
import fa.training.entity.EntryTest;
import fa.training.entity.Interview;
import fa.training.entity.TraineeProfile;
import fa.training.mapper.CandidateMapper;
import fa.training.mapper.EntryTestMapper;
import fa.training.mapper.InterviewMapper;
import fa.training.model.CandidateDto;
import fa.training.model.CandidateForm;
import fa.training.model.CandidateListPage;
import fa.training.model.EntryTestDto;
import fa.training.model.InterviewDto;
import fa.training.model.RestCreateCandidate;
import fa.training.repository.CandidateRepository;
import fa.training.repository.EntryTestRepository;
import fa.training.repository.InterviewRepository;
import fa.training.repository.TraineeProfileRepository;
import fa.training.service.CandidateService;
import fa.training.util.CandidateStatusList;
import fa.training.util.CandidateType;
import fa.training.util.CandidateValidation;
import fa.training.util.EmailSender;
import fa.training.util.TransferLocationList;

@Service
@Transactional(rollbackFor = Exception.class)
public class CandidateServiceImpl implements CandidateService {
	@Autowired
	private CandidateRepository candidateRepository;
	@Autowired
	private InterviewRepository interviewRepository;
	@Autowired
	private EntryTestRepository entryTestRepository;
	@Autowired
	private TraineeProfileRepository traineeProfileRepository;
	@Autowired
	private CandidateValidation candidateValidation;
	@Autowired
	private EmailSender emailSender;

	@Override
	public boolean createCandidate(RestCreateCandidate restCreateCandidate) {
		try {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			TraineeProfile traineeProfile = new TraineeProfile(restCreateCandidate.getCandidateForm());
			traineeProfile.setType(CandidateType.TYPE);
			traineeProfile.setAccount(generateAccount(traineeProfile.getFullName()));
			traineeProfileRepository.save(traineeProfile);

			Candidate candidate = new Candidate(restCreateCandidate.getCandidateForm(), traineeProfile.getId());
			candidate.setStatus(CandidateStatusList.CANDIDATE_STATUS_NEW);
			candidate.setHistory(new Date() + " - Created by " + ((UserDetails) principal).getUsername());
			candidate.setDelete(false);

			if (candidate.getApplicationDate() == null) {
				candidate.setApplicationDate(LocalDate.now());
			}
			candidateRepository.save(candidate);

			if (candidateValidation.isHasInterview(restCreateCandidate.getInterviewDto())) {
				List<Interview> interviewList = interviewRepository.findByCandidateId(candidate.getId());
				Interview interview = InterviewMapper.INSTANCE.dtoToEntity(restCreateCandidate.getInterviewDto());
				interview.setTime(Long.valueOf(interviewList.size() + 1));
				interview.setCandidateId(candidate.getId());
				interview.setDelete(false);
				interviewRepository.save(interview);
			}

			if (candidateValidation.isHasEntryTest(restCreateCandidate.getEntryTestDto())) {
				List<EntryTest> entryTestList = entryTestRepository.findByCandidateId(candidate.getId());
				EntryTest entryTest = EntryTestMapper.INSTANCE.dtoToEntity(restCreateCandidate.getEntryTestDto());
				entryTest.setTime(Long.valueOf(entryTestList.size() + 1));
				entryTest.setCandidateId(candidate.getId());
				entryTest.setDelete(false);
				entryTestRepository.save(entryTest);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
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

	@Override
	public CandidateDto getCandidateById(Long id) {
		Candidate candidate = candidateRepository.findByIsDeleteAndId(false, id).get();
		return candidate != null ? CandidateMapper.INSTANCE.entityToDto(candidate) : null;
	}

	@Override
	public CandidateForm getCandidateFormByCandidateId(Long id) {
		Candidate candidate = candidateRepository.findByIsDeleteAndId(false, id).get();
		if (candidate != null) {
			TraineeProfile traineeProfile = traineeProfileRepository.getById(candidate.getProfileId());
			return new CandidateForm(candidate, traineeProfile);
		}
		return null;
	}

	@Override
	public boolean updateCandidate(CandidateForm candidateForm) {
		try {
			Candidate candidate = candidateRepository.findByIsDeleteAndId(false, candidateForm.getCanId()).get();
			if (candidate != null) {
				Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				candidate.updateCanidate(candidateForm);
				if (candidate.getApplicationDate() == null) {
					candidate.setApplicationDate(LocalDate.now());
				}
				candidate.setHistory(candidate.getHistory() + ". " + new Date() + " - Updated by "
						+ ((UserDetails) principal).getUsername());

				TraineeProfile traineeProfile = traineeProfileRepository.getById(candidate.getProfileId());
				traineeProfile.setId(candidate.getProfileId());
				traineeProfile.updateTraineeProfile(candidateForm);
				traineeProfileRepository.save(traineeProfile);

				if (candidateForm.getEntryTests() != null) {
					List<EntryTest> entryTestCheck = entryTestRepository.findByIsDeleteAndCandidateId(false,
							candidate.getId());
					for (EntryTest entry : entryTestCheck) {
						boolean check = candidateForm.getEntryTests().stream()
								.anyMatch(ent -> (ent.getTestId() != null && ent.getTestId() == entry.getTestId()));
						if (!check) {
							entry.setDelete(true);
							entryTestRepository.save(entry);
						}
					}
					for (EntryTestDto entry : candidateForm.getEntryTests()) {
						entry.setCandidateId(candidate.getId());
						if (entry.getTestId() != null) {
							EntryTest entryTest = entryTestRepository.getById(entry.getTestId());
							if (!candidateValidation.isHasEntryTest(entry)) {
								entryTest.setDelete(true);
							} else {
								// update status entry test => update history
								if (!entry.getResultEntry().equals(entryTest.getResult())) {
									candidate.setHistory(candidate.getHistory() + ". Test - " + entry.getResultEntry()
											+ " - " + new Date() + " - " + entry.getResultEntry() + "  Updated by "
											+ ((UserDetails) principal).getUsername());
								}
								entryTest.updateEntryTest(entry);
							}
							entryTestRepository.save(entryTest);
						} else if (candidateValidation.isHasEntryTest(entry)) {
							List<EntryTest> entryTestList = entryTestRepository.findByCandidateId(candidate.getId());
							entry.setTime(Long.valueOf(entryTestList.size() + 1));
							entryTestRepository.save(EntryTestMapper.INSTANCE.dtoToEntity(entry));
						}
					}
				}
				if (candidateForm.getInterviews() != null) {
					List<Interview> interviewCheck = interviewRepository.findByIsDeleteAndCandidateId(false,
							candidate.getId());
					for (Interview interview : interviewCheck) {
						boolean check = candidateForm.getInterviews().stream()
								.anyMatch(inter -> (inter.getId() != null && inter.getId() == interview.getId()));
						if (!check) {
							interview.setDelete(true);
							interviewRepository.save(interview);
						}
					}
					for (InterviewDto interview : candidateForm.getInterviews()) {
						interview.setCandidateId(candidate.getId());
						if (interview.getId() != null) {
							Interview interviewGet = interviewRepository.getById(interview.getId());
							if (!candidateValidation.isHasInterview(interview)) {
								interviewGet.setDelete(true);
							} else {
								// update status interview => update history
								if (!interview.getResult().equals(interviewGet.getResult())) {
									candidate.setHistory(candidate.getHistory() + ". Test - " + interview.getResult()
											+ " - " + new Date() + " - " + interview.getResult() + "  Updated by "
											+ ((UserDetails) principal).getUsername());
								}
								interviewGet.updateInterview(interview);
							}
							interviewRepository.save(interviewGet);
						} else if (candidateValidation.isHasInterview(interview)) {
							List<Interview> interviewList = interviewRepository.findByCandidateId(candidate.getId());
							interview.setTime(Long.valueOf(interviewList.size() + 1));
							interviewRepository.save(InterviewMapper.INSTANCE.dtoToEntity(interview));
						}
					}
				}
				candidateRepository.save(candidate);
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean deleteCandidate(Long id) {
		try {
			Candidate candidate = candidateRepository.findByIsDeleteAndId(false, id).get();
			if (candidate != null) {
				candidate.setDelete(true);
				candidateRepository.save(candidate);
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean transferCandidate(Long id, String transferLocation) {
		try {
			Candidate candidate = candidateRepository.findByIsDeleteAndId(false, id).get();
			if (candidate != null
					&& candidate.getStatus().equals(CandidateStatusList.CANDIDATE_STATUS_INTERVIEW_PASS)) {
				candidate.setStatus(CandidateStatusList.CANDIDATE_STATUS_TRANSFERRED);
				TraineeProfile traineeProfile = traineeProfileRepository.getById(candidate.getProfileId());
				switch (transferLocation) {
				case TransferLocationList.FA:
					emailSender.sendEmailToCadidateTransferred(traineeProfile, " has been transferred to FA.",
							"ET15_template.html");
					break;
				case TransferLocationList.CAMPUS:
					emailSender.sendEmailToCadidateTransferred(traineeProfile, " has been transferred to Campus.",
							"ET14_template.html");
					break;
				default:
					emailSender.sendEmailToCadidateTransferred(traineeProfile, " has been transferred to Internship.",
							"ET16_template.html");
					break;
				}
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<CandidateListPage> getCandidateList(int pageSize, int pageNumber) {
		List<CandidateListPage> result = new ArrayList<CandidateListPage>();
		pageNumber -= 1;
		if (pageNumber >= 0) {
			Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").ascending());
			List<Candidate> candidates = candidateRepository.findByIsDelete(false, pageable);
			for (Candidate candidate : candidates) {
				TraineeProfile traineeProfile = traineeProfileRepository.findById(candidate.getProfileId()).get();
				if (traineeProfile != null) {
					result.add(new CandidateListPage(candidate.getId(), traineeProfile.getAccount(),
							traineeProfile.getFullName(), candidate.getStatus()));
				}
			}
		}
		return result;
	}

	@Override
	public int getAllCandidateAmount() {
		return candidateRepository.getAllCandidateAmount(false);
	}

	@Override
	public boolean deleteManyCandidate(List<Long> canIds) {
		try {
			for (Long id : canIds) {
				Candidate candidate = candidateRepository.findByIsDeleteAndId(false, id).get();
				// not check candidate not equal null because I want to catch exception make roll back
				candidate.setDelete(true);
				candidateRepository.save(candidate);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
