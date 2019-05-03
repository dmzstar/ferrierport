package com.weifan.ferrier.springboot.security.logout;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MyLogoutHandler extends SecurityContextLogoutHandler{
	
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {		
		logger.info("");
		super.logout(request, response, authentication);
	}
	
}
