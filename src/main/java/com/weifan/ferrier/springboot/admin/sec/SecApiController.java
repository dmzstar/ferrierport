package com.weifan.ferrier.springboot.admin.sec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.weifan.ferrier.springboot.security.model.User;
import com.weifan.ferrier.springboot.security.repository.UserRepository;
import com.weifan.ferrier.springboot.security.service.UserService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller("admin_api_secController")
@RequestMapping("/admin/api/secs")
public class SecApiController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;
	
	@GetMapping("")
	@ResponseBody
	public Object index(@RequestParam Integer start,@RequestParam Integer length,@RequestParam Integer draw) {
		
		log.info(start + "");
		log.info(length + "");
		
		if(!userRepository.existsByUsername("aaa")) {
			userService.create("aaa", "123456", "ROLE_ADMIN");
			for(int i=0;i<100;i++) {
				userService.create("admin" + i, "123456", "ROLE_ADMIN");
			}
		}
		
		var page = userRepository.findAllFetch(PageRequest.of(Math.round(start/length), length));
		log.info("$$$$$$$$$$$$$$$$" + start + "," + length);
		return new Datatables.ArrayData<User>(page,draw).rowMap((i,u) -> {
			switch(i) {
				case 0 : return u.getId() + "";
				case 1 : return u.getUsername() + "";
				default : return "";
			}
		});
		
	}
	
	@GetMapping("/user/{id}")
	@ResponseBody
	public Object get(@PathVariable Long id) {
		var userOpt = userRepository.findById(id);
		if(userOpt.isPresent()) {
			var user = userOpt.get();
			var map = new HashMap<>();
			map.put("id", user.getId());
			map.put("username", user.getUsername());
			return map;
		}else {
			return new HashMap<>();
		}
	}
	


}
