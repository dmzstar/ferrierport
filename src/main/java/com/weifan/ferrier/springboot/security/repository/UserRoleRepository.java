package com.weifan.ferrier.springboot.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weifan.ferrier.springboot.security.model.UserRole;
import com.weifan.ferrier.springboot.security.model.UserRole.UserRoleId;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
	
}
