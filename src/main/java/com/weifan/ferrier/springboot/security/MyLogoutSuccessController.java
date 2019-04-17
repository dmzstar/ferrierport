package com.weifan.ferrier.springboot.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MyLogoutSuccessController {
	
	@GetMapping("/logout/success")
	public String logoutSucess() {
		System.out.println("===============myLogoutSuccessController");
		return "redirect:/";
	}

}
