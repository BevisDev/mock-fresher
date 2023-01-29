package fa.training.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fa.training.model.AuthDto;
import fa.training.model.ResponseObject;
import fa.training.service.AuthService;
import fa.training.util.MESSAGE;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
@Slf4j
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody AuthDto authDto) {
		ResponseEntity<Object> responseEntity = ResponseEntity.badRequest()
				.body(MESSAGE.MSG1.msg);
		try {
			String token = authService.login(authDto);
			responseEntity = ResponseEntity.ok()
					.body(new ResponseObject(String.valueOf(HttpStatus.OK), "LOGIN IS SUCCEED", token));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Login failed");
		}
		return responseEntity;
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody AuthDto authDto) {
		ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		try {
			authService.register(authDto);
			responseEntity = ResponseEntity.ok()
					.body(new ResponseObject(String.valueOf(HttpStatus.CREATED), "REGISTER IS SUCCEED", true));
		} catch (Exception e) {
			log.error("Register failed");
		}
		return responseEntity;
	}
}
