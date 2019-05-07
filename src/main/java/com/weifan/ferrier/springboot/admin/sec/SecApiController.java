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
		return new BoostrapDatatableData<User>(page,draw).rowMap((i,u) -> {
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
	
	/**
	 * 
	 * @author dong
	 * @param <T>
	 */
	@Data
	public static class BoostrapDatatableData<T>{
		
		@JsonIgnore
		private Page<T> page;
		
		/**
		 * 
		 * @param page
		 * @param draw 构造函数draw参数客户端生成并传递，否则表格渲染出错
		 */
		public BoostrapDatatableData(Page<T> page,int draw) {
			this.recordsTotal = page.getTotalElements();
			this.recordsFiltered = page.getTotalElements();
			this.draw = draw;
			this.page = page;
		}
		
		public BoostrapDatatableData<T> rowMap(Fun<T> fun) {
			var i = 0;
			for(var item : page.getContent()) {
				var itemList = new ArrayList<String>();
				for(int j=0;j<=1;j++) {
					itemList.add(fun.todo(j,item));
				}
				itemList.add("" + (i++));
				data.add(itemList);
			}
			/**
			page.getContent().forEach((item) -> {
				var itemList = new ArrayList<String>();
				for(int j=0;j<=1;j++) {
					itemList.add("" + (i++));
					itemList.add(fun.todo(j,item));
				}
				data.add(itemList);
			});
			*/
			return this;
		}
		
		private Integer draw = 1;
		private Long recordsTotal = 0l;
		private Long recordsFiltered = 0l;
		private List<List<String>> data = new ArrayList<>();
		
		public static interface Fun<T>{
			public String todo(int index,T t);
		}
		
	}

}
