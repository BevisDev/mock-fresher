package fa.training.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fa.training.model.ResponseObject;

@RestController
@RequestMapping("/api/logged")
@CrossOrigin
public class LoggedController {
	@GetMapping
	public ResponseEntity<ResponseObject> logged() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			return ResponseEntity.ok().body(new ResponseObject(String.valueOf(HttpStatus.OK), "LOGGED", true));
		}
		return ResponseEntity.ok()
				.body(new ResponseObject(String.valueOf(HttpStatus.EXPECTATION_FAILED), "NOT LOGGED", false));
	}
}
