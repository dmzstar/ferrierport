package com.weifan.ferrier.springboot.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("admin_indexController")
public class IndexController {
	
	@GetMapping("/admin")
	public String index() {
		System.out.println("===========AdminIndexController");
		return "index";
	}

}
