package fa.training.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class IndexController {

	@GetMapping("/index")
	public String index(@ModelAttribute("token") String token, HttpSession session)
	{
		System.out.println("token : " + session.getAttribute("token"));
		return "index";
	}
}
