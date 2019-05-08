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

		@GetMapping("/sidebar/starter")
		public String sidebar(ModelMap model) {
			return cocView();
		}
		
		@GetMapping("/blog/starter")
		public String blog(ModelMap model) {
			return cocView();
		}
		
		@GetMapping("/admin/starter")
		public String admin(ModelMap model) {
			return cocView();
		}
		
		@GetMapping("/admin/starter_tf")
		public String admin_starter_tf(ModelMap model) {
			return cocView();
		}
		
		@GetMapping("/shop/index")
		public String shop(ModelMap model) {
			return cocView();
		}
		
		@GetMapping("/bare/index")
		public String bare(ModelMap model) {
			return cocView();
		}

	}

}
