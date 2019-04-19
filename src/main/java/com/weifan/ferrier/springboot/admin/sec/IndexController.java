package com.weifan.ferrier.springboot.admin.sec;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("admin_secController")
@RequestMapping("/admin")
public class IndexController {
	
	@GetMapping("/secs")
	public String index() {
		System.out.println("===========AdminIndexControllersssss");
		return "admin/secs/index";
	}

}
