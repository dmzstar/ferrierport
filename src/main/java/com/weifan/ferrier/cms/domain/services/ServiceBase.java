package com.weifan.ferrier.cms.domain.services;

import org.springframework.beans.factory.annotation.Autowired;

public interface ServiceBase<R> {
	
	@Autowired
	default public void setRepository(R r){
		
	}

}
