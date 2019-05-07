package com.weifan.ferrier.cms.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Data;

@Data
@Entity
public class Article {
	
	@Id
	@GeneratedValue
	private Long id;
	@Column(unique = true)
	private String code;
	@Column(unique = true)
	private String title;
	@Lob
	private String details;

}
