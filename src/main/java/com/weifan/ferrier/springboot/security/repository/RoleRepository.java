package com.weifan.ferrier.springboot.security.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.weifan.ferrier.springboot.security.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	public Role findByName(String name);
	public boolean existsByName(String name);
	
	@Query(value = "from Role o order by o.id desc",countQuery = "select count(o) from Role o")
	public Page<Role> findAllFetch(Pageable pageable);
	
}
