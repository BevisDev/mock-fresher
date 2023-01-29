package fa.training.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fa.training.entity.ClassAdminProfile;
import fa.training.entity.ClassBatch;
import fa.training.entity.TraineeProfile;
import fa.training.entity.TrainerProfile;
import fa.training.entity.User;
import fa.training.repository.ClassAdminProfileRepository;
import fa.training.service.EmailService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EmailSender {

	@Autowired
	private ClassAdminProfileRepository classAdminProfileRepository;
	@Autowired
	private EmailService emailService;

	public void sendEmailToClassAdminET1(List<Long> adminIds, ClassBatch classBatch) {
		String subject = "[FA MANAGEMENT]: The Class " + classBatch.getClassCode() + " is assigned to you.";

		for (Long adminId : adminIds) {
			ClassAdminProfile admin = classAdminProfileRepository.findById(adminId).get();
			Map<String, Object> templateModel = new HashMap<>();
			templateModel.put("adminName", admin.getFullName());
			templateModel.put("classCode", classBatch.getClassCode());
			templateModel.put("className", classBatch.getClassName());
			try {
				emailService.sendMessageUsingThymeleafTemplate(new String[] { admin.getEmail() }, subject,
						new String[] {}, "ET1_template.html", templateModel);
			} catch (MessagingException e) {
				log.error("Send email failed");
			}
		}
	}

	public void sendEmailToClassAdminET2(List<Long> adminIds, ClassBatch classBatch) {
		String subject = "[FA MANAGEMENT]: The Class " + classBatch.getClassCode() + " is updated.";

		for (Long adminId : adminIds) {
			ClassAdminProfile admin = classAdminProfileRepository.findById(adminId).get();
			Map<String, Object> templateModel = new HashMap<>();
			templateModel.put("adminName", admin.getFullName());
			templateModel.put("classCode", classBatch.getClassCode());
			templateModel.put("className", classBatch.getClassName());
			try {
				emailService.sendMessageUsingThymeleafTemplate(new String[] { admin.getEmail() }, subject,
						new String[] {}, "ET2_template.html", templateModel);
			} catch (MessagingException e) {
				log.error("Send email failed");
			}
		}
	}

	public void sendEmailToClassOperatorsET4(List<ClassAdminProfile> admins, TrainerProfile trainerMaster,
			List<TrainerProfile> trainers, ClassBatch classBatch) {
		String subject = "[FA MANAGEMENT]: The Class " + classBatch.getClassCode() + " has been cancelled.";
		Map<String, Object> templateModel = new HashMap<>();
		// sending to master trainer
		templateModel.put("masterTrainerName", trainerMaster.getFullName());
		templateModel.put("classCode", classBatch.getClassCode());
		templateModel.put("className", classBatch.getClassName());
		templateModel.put("adminName", "");
		templateModel.put("trainerName", "");
		try {
			emailService.sendMessageUsingThymeleafTemplate(new String[] { trainerMaster.getEmail() }, subject,
					new String[] {}, "ET4_template.html", templateModel);
		} catch (MessagingException e) {
			log.error("Send email failed");
		}
		// sending to admins
		for (ClassAdminProfile admin : admins) {
			// ClassAdminProfile admin =
			// classAdminProfileRepository.findById(adminId).get();
			templateModel = new HashMap<>();
			templateModel.put("adminName", admin.getFullName());
			templateModel.put("classCode", classBatch.getClassCode());
			templateModel.put("className", classBatch.getClassName());
			templateModel.put("masterTrainerName", "");
			templateModel.put("trainerName", "");
			try {
				emailService.sendMessageUsingThymeleafTemplate(new String[] { admin.getEmail() }, subject,
						new String[] {}, "ET4_template.html", templateModel);
			} catch (MessagingException e) {
				log.error("Send email failed");
			}
		}

		// sending to trainers
		for (TrainerProfile trainer : trainers) {
			templateModel = new HashMap<>();
			templateModel.put("trainerName", trainer.getFullName());
			templateModel.put("classCode", classBatch.getClassCode());
			templateModel.put("className", classBatch.getClassName());
			templateModel.put("adminName", "");
			templateModel.put("masterTrainerName", "");
			try {
				emailService.sendMessageUsingThymeleafTemplate(new String[] { trainer.getEmail() }, subject,
						new String[] {}, "ET4_template.html", templateModel);
			} catch (MessagingException e) {
				log.error("Send email failed");
			}
		}
	}

	public void sendEmailToDeliveryManagersET5(ClassBatch classBatch) {
		String subject = "[FA MANAGEMENT]: The Class " + classBatch.getClassCode() + " needs your approval.";
		Map<String, Object> templateModel = new HashMap<>();
		templateModel = new HashMap<>();
		templateModel.put("deliveryName", DELIVERY_MANAGER_INFO.FULLNAME.info);
		templateModel.put("classCode", classBatch.getClassCode());
		templateModel.put("className", classBatch.getClassName());
		try {
			emailService.sendMessageUsingThymeleafTemplate(new String[] { DELIVERY_MANAGER_INFO.EMAIL.info }, subject,
					new String[] {}, "ET5_template.html", templateModel);
		} catch (MessagingException e) {
			log.error("Send email failed");
		}
	}

	public void sendEmailToFAManagerET6(ClassBatch classBatch) {
		String subject = "[FA MANAGEMENT]: The Class " + classBatch.getClassCode() + " needs your acceptance.";
		Map<String, Object> templateModel = new HashMap<>();
		templateModel = new HashMap<>();
		templateModel.put("faManagerName", FA_MANAGER_INFO.FULLNAME.info);
		templateModel.put("classCode", classBatch.getClassCode());
		templateModel.put("className", classBatch.getClassName());
		try {
			emailService.sendMessageUsingThymeleafTemplate(new String[] { FA_MANAGER_INFO.EMAIL.info }, subject,
					new String[] {}, "ET6_template.html", templateModel);
		} catch (MessagingException e) {
			log.error("Send email failed");
		}
	}

	public void sendEmailToClassOperatorsET7(List<ClassAdminProfile> admins, TrainerProfile trainerMaster,
			List<TrainerProfile> trainers, ClassBatch classBatch) {
		String subject = "[FA MANAGEMENT]: The Class " + classBatch.getClassCode() + " has been rejected.";
		Map<String, Object> templateModel = new HashMap<>();
		// sending to master trainer
		templateModel.put("masterTrainerName", trainerMaster.getFullName());
		templateModel.put("classCode", classBatch.getClassCode());
		templateModel.put("className", classBatch.getClassName());
		templateModel.put("adminName", "");
		templateModel.put("trainerName", "");
		try {
			emailService.sendMessageUsingThymeleafTemplate(new String[] { trainerMaster.getEmail() }, subject,
					new String[] {}, "ET7_template.html", templateModel);
		} catch (MessagingException e) {
			log.error("Send email failed");
		}
		// sending to admins
		for (ClassAdminProfile admin : admins) {
			// ClassAdminProfile admin =
			// classAdminProfileRepository.findById(adminId).get();
			templateModel = new HashMap<>();
			templateModel.put("adminName", admin.getFullName());
			templateModel.put("classCode", classBatch.getClassCode());
			templateModel.put("className", classBatch.getClassName());
			templateModel.put("masterTrainerName", "");
			templateModel.put("trainerName", "");
			try {
				emailService.sendMessageUsingThymeleafTemplate(new String[] { admin.getEmail() }, subject,
						new String[] {}, "ET7_template.html", templateModel);
			} catch (MessagingException e) {
				log.error("Send email failed");
			}
		}

		// sending to trainers
		for (TrainerProfile trainer : trainers) {
			templateModel = new HashMap<>();
			templateModel.put("trainerName", trainer.getFullName());
			templateModel.put("classCode", classBatch.getClassCode());
			templateModel.put("className", classBatch.getClassName());
			templateModel.put("adminName", "");
			templateModel.put("masterTrainerName", "");
			try {
				emailService.sendMessageUsingThymeleafTemplate(new String[] { trainer.getEmail() }, subject,
						new String[] {}, "ET7_template.html", templateModel);
			} catch (MessagingException e) {
				log.error("Send email failed");
			}
		}
	}

	public void sendEmailToClassAdminET13(List<ClassAdminProfile> admins, ClassBatch classBatch) {
		String subject = "[FA MANAGEMENT]: The Class " + classBatch.getClassCode()
				+ " has been request for more information";

		for (ClassAdminProfile admin : admins) {
			Map<String, Object> templateModel = new HashMap<>();
			templateModel.put("adminName", admin.getFullName());
			templateModel.put("classCode", classBatch.getClassCode());
			templateModel.put("className", classBatch.getClassName());
			try {
				emailService.sendMessageUsingThymeleafTemplate(new String[] { admin.getEmail() }, subject,
						new String[] {}, "ET13_template.html", templateModel);
			} catch (MessagingException e) {
				log.error("Send email failed");
			}
		}
	}

	public void sendEmailToCadidateTransferred(TraineeProfile traineeProfile, String title, String template) {
		String subject = "[FA MANAGEMENT]:" + traineeProfile.getFullName() + title;
		Map<String, Object> templateModel = new HashMap<>();
		templateModel.put("candidateName", traineeProfile.getFullName());
		try {
			emailService.sendMessageUsingThymeleafTemplate(new String[] { traineeProfile.getEmail() }, subject,
					new String[] {}, template, templateModel);
		} catch (MessagingException e) {
			log.error("Send email failed");
		}
	}

	public void sendEmailChangeStatusClass(ClassBatch classBatch, List<ClassAdminProfile> classAdminProfiles,
			TrainerProfile masterTrainer, List<TrainerProfile> trainers, String title, String template) {
		String subject = "[FA MANAGEMENT]: The Class " + classBatch.getClassCode() + title;
		Map<String, Object> templateModel = new HashMap<>();
		templateModel.put("classCode", classBatch.getClassCode());
		templateModel.put("className", classBatch.getClassName());
		templateModel.put("adminNames", classAdminProfiles);
		try {
			templateModel.put("trainers", trainers);
			if (masterTrainer != null) {
				templateModel.put("masterTrainer", masterTrainer);
				emailService.sendMessageUsingThymeleafTemplate(new String[] { masterTrainer.getEmail() }, subject,
						new String[] {}, template, templateModel);
			}
			for (TrainerProfile trainerProfile : trainers) {
				emailService.sendMessageUsingThymeleafTemplate(new String[] { trainerProfile.getEmail() }, subject,
						new String[] {}, template, templateModel);
			}
			for (ClassAdminProfile admin : classAdminProfiles) {
				emailService.sendMessageUsingThymeleafTemplate(new String[] { admin.getEmail() }, subject,
						new String[] {}, template, templateModel);
			}

		} catch (MessagingException e) {
			log.error("Send email failed");
		}
	}

	public void sendEmailToDeliveryManagerET11(String deliveryEmail, String deliveryFullName, ClassBatch classBatch) {
		String subject = "[DELIVERY MANAGER] The class:" + classBatch.getClassCode() + " has finished.";
			Map<String, Object> templateModel = new HashMap<>();
			templateModel.put("deliveryName",deliveryFullName);
			templateModel.put("classCode", classBatch.getClassCode());
			templateModel.put("className", classBatch.getClassName());

			try {
				emailService.sendMessageUsingThymeleafTemplate(new String[] { deliveryEmail }, subject,
						new String[] {}, "ET11_template.html", templateModel);
			} catch (MessagingException e) {
				log.error("Send email failed");

		}
	}

	public void sendEmailToManager(List<TraineeProfile> managers, ClassBatch classBatch) {
		String subject = "[FA MANAGER] The class: " + classBatch.getClassCode() + " has closed.";
		for (TraineeProfile manager : managers) {
			Map<String, Object> templateModel = new HashMap<>();
			templateModel.put("managerName", manager.getFullName());
			templateModel.put("classCode", classBatch.getClassCode());
			templateModel.put("className", classBatch.getClassName());

			try {
				emailService.sendMessageUsingThymeleafTemplate(new String[] { manager.getEmail() }, subject,
						new String[] {}, "ET12_template.html", templateModel);
			} catch (MessagingException e) {
				log.error("Send email failed");
			}
		}

	}

}
