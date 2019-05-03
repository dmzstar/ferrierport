package com.weifan.ferrier.springboot.security.event;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.weifan.ferrier.springboot.core.Beans;
import com.weifan.ferrier.springboot.security.model.UserRole;
import com.weifan.ferrier.springboot.security.repository.UserRoleRepository;
import com.weifan.ferrier.springboot.security.service.RoleService;
import com.weifan.ferrier.springboot.security.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ApplicationEventListener {
	
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	
	@EventListener
	public void on(ApplicationPreparedEvent event) {
		log.info("==============================" + event + "");
	}
	
	@EventListener
	@Transactional
	public void on(ApplicationStartedEvent event) {
		
		log.info(event + "");
		Beans.context = event.getApplicationContext();
		roleService.create("ROLE_ADMIN");
		userService.create("admin", "123456","ROLE_ADMIN");
		
		var userRepo = userService.getRepository();
		var roleRepo = roleService.getRepository();
		var userRoleReop = Beans.repository(UserRoleRepository.class);
		
		var userRole = new UserRole();
		userRole.setRole(roleRepo.findByName("ROLE_ADMIN"));
		userRole.setUser(userRepo.findByUsername("admin"));
		
		userRoleReop.save(userRole);
		
	}
	
	@EventListener
	public void on(ApplicationEnvironmentPreparedEvent event) {
		log.info("==============================" + event + "");
	}
	
	@EventListener
	public void on(ApplicationFailedEvent event) {
		log.info("==============================" + event + "");
	}

}
