package com.weifan.ferrier.springboot.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weifan.ferrier.springboot.security.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	public Role findByName(String name);
	public boolean existsByName(String name);
	
}
