package com.weifan.ferrier.showcase.primeui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/showcase/primeui/datatables")
public class ShowCasePrimeUIController implements COCViewController{
	
	@GetMapping("/contextmenu")
	public String contextmenu() {
		return cocView();
	}

}
