package com.weifan.ferrier.springboot.security.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.weifan.ferrier.springboot.security.model.Role;
import com.weifan.ferrier.springboot.security.repository.RoleRepository;

import lombok.Getter;

@Service
public class RoleService {
	
	@Getter
	@Autowired
	private RoleRepository repository;
	
	public <T> Page<T> findPage(RepoCall<T> call) {
		return call.call(repository);
	}
	
	public static interface RepoCall<T>{
		public Page<T> call(RoleRepository repo);
	}
	
	@Transactional
	public void create(String ... roleNames) {
		for(var roleName : roleNames) {
			create(roleName);
		}
	}
	
	@Transactional
	public void create(String roleName) {
		if(repository.existsByName(roleName)) {
			throw new RuntimeException("role.exists");
		}
		Role role = new Role();
		role.setName(roleName);
		repository.save(role);
	}
	
	public boolean isExists(String name) {
		return repository.existsByName(name);
	}

}
