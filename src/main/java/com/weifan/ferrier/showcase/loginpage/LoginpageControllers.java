package com.weifan.ferrier.showcase.loginpage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weifan.ferrier.springboot.mvc.COCViewController;

import lombok.extern.slf4j.Slf4j;

public class LoginpageControllers {
	
	@Slf4j
	@Controller
	@RequestMapping("/showcase/loginpage")
	public static class Index implements COCViewController{

		@GetMapping("/form-1/index")
		public String sidebar(ModelMap model) {
			return cocView();
		}
		
		@GetMapping("/form-2/index")
		public String blog(ModelMap model) {
			return cocView();
		}
		
		@GetMapping("/form-3/index")
		public String admin(ModelMap model) {
			return cocView();
		}

	}

}
