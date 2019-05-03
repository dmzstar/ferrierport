package com.weifan.ferrier.springboot.security.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="tb_group")
public class Group {
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;

}
