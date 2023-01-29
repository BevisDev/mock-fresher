package fa.training.service;

import java.util.Map;

import javax.mail.MessagingException;

public interface EmailService {

	public void sendHtmlMessage(String to[], String subject, String cc[], String htmlBody) throws MessagingException;

	public void sendMessageUsingThymeleafTemplate(String to[], String subject, String cc[], String template,
			Map<String, Object> templateModel) throws MessagingException;
}
