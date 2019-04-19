package com.weifan.ferrier.springboot.admin.users.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("admin_userController")
@RequestMapping("/admin")
public class IndexController {
	
	@GetMapping("/users")
	public String index() {
		System.out.println("===========AdminIndexControllersssss");
		return "admin/users/index";
	}

}
