package com.weifan.ferrier.cms.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;

import com.weifan.ferrier.cms.domain.repositories.CmsPageRepository;

import lombok.Data;

@Data
@Entity
public class CmsPage {
	
	@Id
	@GeneratedValue
	private Long id;
	@Column(unique = true)
	private String name;
	
	@Autowired
	@Transient
	private CmsPageRepository repository;

}
