package com.weifan.ferrier.springboot.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

@Component
public class MyLogoutHandler extends SecurityContextLogoutHandler{
	
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {		
		System.out.println("===============myLogoutHandler");
		super.logout(request, response, authentication);
	}
	
}
