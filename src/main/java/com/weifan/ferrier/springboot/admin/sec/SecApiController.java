package com.weifan.ferrier.springboot.admin.sec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.weifan.ferrier.springboot.security.model.User;
import com.weifan.ferrier.springboot.security.repository.UserRepository;

import lombok.Data;

@Controller("admin_api_secController")
@RequestMapping("/admin/api/secs")
public class SecApiController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("")
	@ResponseBody
	public Object index() {
		var page = userRepository.findAll(PageRequest.of(0, 100));
		return new BoostrapDatatableData<User>(page).collect((i,u) -> {
			if(i == 0) {
				return u.getId() + "";
			}
			if(i == 1) {
				return u.getUsername() + "";
			}
			return "";
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
			//data.add(Arrays.asList("1","admin"));
		}
		
		public BoostrapDatatableData<T> collect(Fun<T> fun) {
			page.getContent().forEach((item) -> {
				var itemList = new ArrayList<String>();
				for(int j=0;j<=1;j++) {
					itemList.add(fun.todo(j,item));
				}
				data.add(itemList);
			});
			return this;
		}
		
		private Integer draw = 1;
		private String recordsTotal = "57";
		private String recordsFiltered = "57";
		private List<List<String>> data = new ArrayList<>();
		
		public static interface Fun<T>{
			public String todo(int index,T t);
		}
		
	}

}
