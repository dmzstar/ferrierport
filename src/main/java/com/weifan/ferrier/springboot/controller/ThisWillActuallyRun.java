package com.weifan.ferrier.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ThisWillActuallyRun {

	@RequestMapping("/hello")
	public String home() {
		return "Hello World!";
	}
	
	@RequestMapping("/hi")
	public String hi() {
		return "Hi!";
	}

}