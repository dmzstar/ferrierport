package com.weifan.ferrier.springboot.security.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.weifan.ferrier.springboot.security.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByUsername(String username);
	public boolean existsByUsername(String username);
	
	@Query(value = "from User u join fetch u.roles order by u.id desc",countQuery = "select count(u) from User u")
	public Page<User> findAllFetch(Pageable pageable);
	
}

