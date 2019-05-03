package com.weifan.ferrier.springboot.security.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@Entity
@Table
@Data
@Slf4j
public class UserRole {
	
	@EmbeddedId
	 @AttributeOverrides({  
         @AttributeOverride(name="userId",column=@Column(name="pk_userId")),  
         @AttributeOverride(name="roleId",column=@Column(name="pk_roleId"))  
	 })  
	private UserRoleId id = new UserRoleId();
	
	@ManyToOne
	private User user;
	@ManyToOne
	private Role role;
	
	@PrePersist 
	public void onPrePersist() {
		log.info("userId : " + user.getId() + ", roleId : " + role.getId());
		id.setUserId(user.getId());
		id.setRoleId(role.getId());
	}
	
	@Data
	public static class UserRoleId implements Serializable{
		private Long userId;
		private Long roleId;
	}
	
	
	
}
