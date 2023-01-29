package fa.training.controller;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fa.training.entity.User;
import fa.training.filter.JwtProvider;
import fa.training.model.UserDetailsDto;
import fa.training.repository.UserRepository;
import fa.training.service.EmailService;

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private JwtProvider jwtProvider;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private EmailService emailService;

	@GetMapping("/not-author")
	public ResponseEntity<?> test() {
		return new ResponseEntity<Object>("Test ne", HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<?> testToken(@RequestBody User user) {
		UserDetailsDto dto = new UserDetailsDto(userRepository.findByUsername(user.getUsername()));
		return new ResponseEntity<Object>(jwtProvider.generateToken(dto), HttpStatus.OK);
	}

	@GetMapping("/author")
	@PreAuthorize("hasAnyRole('ROLE_CLASS_ADMIN','ROLE_MANAGER')")
	public ResponseEntity<?> testAuthor() {
		return new ResponseEntity<Object>("Author", HttpStatus.OK);
	}

	@PostMapping("/send-email")
	public ResponseEntity<?> testSendEmail() {
		try {
			String username = "MinhNNN";
			Map<String, Object> templateModel = new HashMap<>();
			templateModel.put("username", username);
			emailService.sendMessageUsingThymeleafTemplate(new String[] { "leanhduy.doan@gmail.com" },
					"Test send email", new String[] {}, "ET2_template.html", templateModel);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>("Ok", HttpStatus.OK);
	}
}
