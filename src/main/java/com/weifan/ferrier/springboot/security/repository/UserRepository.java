package com.weifan.ferrier.springboot.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weifan.ferrier.springboot.security.model.SecUser;

public interface UserRepository extends JpaRepository<SecUser, Long> {
	
}

