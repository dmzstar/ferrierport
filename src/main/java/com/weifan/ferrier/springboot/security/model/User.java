package com.weifan.ferrier.springboot.security.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.weifan.ferrier.springboot.security.validation.validator.Username;

import lombok.Data;

@Data
@Entity
@Table(name="tb_user")
public class User implements UserDetails{
	
	@Id
	@GeneratedValue
	private Long id;
	@NotBlank(message = "username.error.blank")
	//@Username
	private String username;
	private String password;
	private boolean enabled = true;
	private boolean accountNonExpired = true;
	private boolean accountNonLocked = true;
	private boolean credentialsNonExpired = true;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Role> roles = new ArrayList<>();
	
	@Transient
	private List<? extends GrantedAuthority> authorities = new ArrayList<>();
	
	public List<? extends GrantedAuthority> getAuthorities(){
		return roles;
	}
	
	
	public boolean hasRole(String name) {
		
		
		return true;
		
	}

}
