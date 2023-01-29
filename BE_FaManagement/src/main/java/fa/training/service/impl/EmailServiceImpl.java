package fa.training.service.impl;

import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import fa.training.service.EmailService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

	private final JavaMailSender emailSender;
	private static final String SYSTEM_ADDRESS = "java2group4@outlook.com";
	private final SpringTemplateEngine thymeleafTemplateEngine;

	public EmailServiceImpl(JavaMailSender emailSender, SpringTemplateEngine thymeleafTemplateEngine) {
		this.emailSender = emailSender;
		this.thymeleafTemplateEngine = thymeleafTemplateEngine;
	}

	@Override
	public void sendHtmlMessage(String to[], String subject, String cc[], String htmlBody) {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setFrom(SYSTEM_ADDRESS);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setCc(cc);
			helper.setText(htmlBody, true);
			emailSender.send(message);
		} catch (MessagingException e) {
			log.error("Send email failed");
		}

	}

	@Override
	public void sendMessageUsingThymeleafTemplate(String to[], String subject, String cc[], String template,
			Map<String, Object> templateModel) {
		Context thymeleafContext = new Context();
		thymeleafContext.setVariables(templateModel);
		String htmlBody = thymeleafTemplateEngine.process(template, thymeleafContext);
		sendHtmlMessage(to, subject, cc, htmlBody);

	}

}
