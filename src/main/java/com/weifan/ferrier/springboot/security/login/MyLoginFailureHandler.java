package com.weifan.ferrier.springboot.security.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MyLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	/**
	@Override
	public void setDefaultFailureUrl(String defaultFailureUrl) {
		super.setDefaultFailureUrl("/login");
	}
	*/
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		//exception.printStackTrace();
		log.info(exception.getMessage());
		super.onAuthenticationFailure(request, response, exception);
		//throw exception;
	}

}
