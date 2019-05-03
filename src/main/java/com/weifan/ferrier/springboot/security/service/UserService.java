package com.weifan.ferrier.springboot.security.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.weifan.ferrier.springboot.security.model.User;
import com.weifan.ferrier.springboot.security.repository.RoleRepository;
import com.weifan.ferrier.springboot.security.repository.UserRepository;

import lombok.Getter;

@Service
public class UserService {
	
	@Getter
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public User create(String username,String password,String ... roleNames) {
		
		if(repository.existsByUsername(username)) {
			throw new RuntimeException("user.error.exists");
		}
		
		var user = new User();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));
		/**
		for(var roleName : roles) {
			var role = roleRepository.findByName(roleName);
			if(role == null) {
				throw new RuntimeException("role.error.notfound");
			}
			user.getRoles().add(role);
		}
		*/
		addRole(user, roleNames);
		repository.save(user);
		return user;
		
	}
	
	public void addRole(Long id,String roleName) {
		var userOpt = repository.findById(id);
		if(userOpt.isEmpty()) {
			throw new RuntimeException("user.error.notfound");
		}
		addRole(userOpt.get(),roleName);
	}
	
	public void addRole(String username,String roleName) {
		var user = repository.findByUsername(username);
		if(user == null) {
			throw new RuntimeException("user.error.notfound");
		}
		addRole(user, roleName);
	}
	
	@Transactional
	public void addRole(User user,String ... roleNames) {
		for(var roleName : roleNames) {
			var role = roleRepository.findByName(roleName);
			if(role == null) {
				throw new RuntimeException("role.error.notfound");
			}
			user.getRoles().add(role);
		}
		repository.save(user);
	}

}
