package com.weifan.ferrier.showcase.bootstrap4;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weifan.ferrier.springboot.mvc.COCViewController;

import lombok.extern.slf4j.Slf4j;

public class Bootstrap4Controllers {
	
	@Slf4j
	@Controller
	@RequestMapping("/showcase/bootstrap4")
	public static class Index implements COCViewController{

		@GetMapping("/simple-sidebar/starter")
		public String sidebar(ModelMap model) {
			return cocView();
		}
		
		@GetMapping("/blog/starter")
		public String index(ModelMap model) {
			return cocView();
		}

	}

}
