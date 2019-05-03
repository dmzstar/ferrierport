package com.weifan.ferrier.springboot.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weifan.ferrier.springboot.security.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByUsername(String username);
	public boolean existsByUsername(String username);
	
}

