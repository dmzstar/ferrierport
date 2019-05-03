package com.weifan.ferrier.springboot.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.weifan.ferrier.springboot.core.Beans;
import com.weifan.ferrier.springboot.security.repository.RoleRepository;

import lombok.Data;

@Data
@Entity
@Table(name="tb_role")
public class Role implements GrantedAuthority{
	
	@Id
	@GeneratedValue
	private Long id;
	@Column(unique = true)
	private String name;

	@Override
	public String getAuthority() {
		return name;
	}

}
