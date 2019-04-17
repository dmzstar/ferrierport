package com.weifan.ferrier.springboot.security.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
@Entity
public class SecUser {
	
	@Id
	@GeneratedValue
	private Long id;
	private String username;
	private String password;
	private boolean enabled;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	@Transient
	private Collection<? extends GrantedAuthority> authorities = new ArrayList<>();

}
