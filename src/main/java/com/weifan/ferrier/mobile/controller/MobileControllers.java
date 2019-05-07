package com.weifan.ferrier.mobile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weifan.ferrier.springboot.mvc.COCViewController;

import lombok.extern.slf4j.Slf4j;

public class MobileControllers {
	
	@Slf4j
	@Controller
	@RequestMapping("/mobile")
	public static class Index implements COCViewController{

		@GetMapping("/index")
		public String index(ModelMap model) {
			return cocView();
		}

	}

}
