package com.weifan.ferrier.springboot.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;


@Component
public class MyLogoutHandler extends SecurityContextLogoutHandler{
	
	private static final Logger logger = LoggerFactory.getLogger(MyLogoutHandler.class);
	
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {		
		logger.info("myLogoutHandler");
		super.logout(request, response, authentication);
	}
	
}
