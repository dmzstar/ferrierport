package com.weifan.ferrier.springboot.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weifan.ferrier.springboot.security.model.SecUser;

public interface SecUserRepository extends JpaRepository<SecUser, Long> {
	
	public SecUser findByUsername(String username);
	
}

