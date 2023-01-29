package fa.training.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import fa.training.model.AuthDto;
import fa.training.service.AuthService;
import fa.training.util.MESSAGE;

@Controller

public class AuthController {

	@Autowired
	private AuthService authService;

	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("authDto", new AuthDto());
		return "auth/login";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute("authDto") AuthDto authDto, Model model, HttpSession session) {
		boolean rs = authService.login(authDto, session);
		if(rs)
		{
			return "redirect:index";
		}else
		{
			model.addAttribute("message", MESSAGE.MSG1.msg);
			return "/auth/login";
		}
	}
	
	@GetMapping("/logout")
	public RedirectView logOut(RedirectAttributes attributes, HttpSession session)
	{
		session.removeAttribute("token");
		session.removeAttribute("userLogin");
		attributes.addFlashAttribute("logOutMessage", "Log out success");
		return new RedirectView("/login");
	}
}
