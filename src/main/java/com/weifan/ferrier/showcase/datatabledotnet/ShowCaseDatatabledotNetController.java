package com.weifan.ferrier.showcase.datatabledotnet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weifan.ferrier.springboot.mvc.COCViewController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/showcase/datatabledotnet")
public class ShowCaseDatatabledotNetController implements COCViewController{
	
	@GetMapping("/local")
	public String localData() {
		return cocView();
	}

}
