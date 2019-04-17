package com.weifan.ferrier.springboot.security.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class SecRole {
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;

}
