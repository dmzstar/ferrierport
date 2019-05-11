package com.weifan.ferrier.springboot.admin.sec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weifan.ferrier.springboot.security.model.Role;
import com.weifan.ferrier.springboot.security.service.RoleService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller("admin_roleController")
@RequestMapping("/admin")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/roles")
	public String index() {
		return "/admin/roles/index";
	}
	
	@GetMapping("/roles/list")
	@ResponseBody
	public Object index(@RequestParam Integer start,@RequestParam Integer length,@RequestParam Integer draw) {
		
		var page = roleService.findPage((r) -> r.findAllFetch(PageRequest.of(Math.round(start/length), length)));
		return new Datatables.ObjectData<Role>(page,draw).rowMap((m,o) -> {
			m.put("id", o.getId());
			m.put("name", o.getName());
		});
		
	}
	
	

	

}
