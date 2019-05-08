package com.weifan.ferrier.showcase.vue2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weifan.ferrier.springboot.mvc.COCViewController;

import lombok.extern.slf4j.Slf4j;

public class Vue2Controllers {
	
	@Slf4j
	@Controller
	@RequestMapping("/showcase/vue2")
	public static class Index implements COCViewController{

		@GetMapping("/bare/index")
		public String index(ModelMap model) {
			return cocView();
		}
		
		@GetMapping("/bare/lifecycle")
		public String lifecycle(ModelMap model) {
			return cocView();
		}

	}

}
