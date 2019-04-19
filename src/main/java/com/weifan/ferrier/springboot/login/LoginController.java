package com.weifan.ferrier.springboot.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping("/login-page")
	public String index() {
		return "login/form-1/index";
	}

}
