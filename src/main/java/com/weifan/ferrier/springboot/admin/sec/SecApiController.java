package com.weifan.ferrier.springboot.admin.sec;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
	public Object index(@RequestParam Integer start,@RequestParam Integer length) {
		
		log.info(start + "");
		log.info(length + "");
		
		if(!userRepository.existsByUsername("aaa")) {
			userService.create("aaa", "123456", "ROLE_ADMIN");
			for(int i=0;i<20;i++) {
				userService.create("admin" + i, "123456", "ROLE_ADMIN");
			}
		}
		
		var page = userRepository.findAllFetch(PageRequest.of(start, length));
		return new BoostrapDatatableData<User>(page).rowMap((i,u) -> {
			switch(i) {
				case 0 : return u.getId() + "";
				case 1 : return u.getUsername() + "";
				default : return "";
			}
		});
		
	}
	
	@Data
	public static class BoostrapDatatableData<T>{
		
		@JsonIgnore
		private Page<T> page;
		
		public BoostrapDatatableData(Page<T> page) {
			this.recordsTotal = page.getTotalElements() + "";
			this.recordsFiltered = page.getTotalElements() + "";
			this.page = page;
		}
		
		public BoostrapDatatableData<T> rowMap(Fun<T> fun) {
			page.getContent().forEach((item) -> {
				var itemList = new ArrayList<String>();
				for(int j=0;j<=1;j++) {
					itemList.add(fun.todo(j,item));
				}
				data.add(itemList);
			});
			return this;
		}
		
		private String draw = "9";
		private String recordsTotal = "0";
		private String recordsFiltered = "0";
		private List<List<String>> data = new ArrayList<>();
		
		public static interface Fun<T>{
			public String todo(int index,T t);
		}
		
	}

}
