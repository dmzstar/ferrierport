package com.weifan.ferrier.springboot.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.weifan.ferrier.springboot.security.MyLogoutHandler;

@EnableWebSecurity
public class HttpSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private MyLogoutHandler logoutHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
		.anyRequest().permitAll().and()
        .formLogin()
        .and()
        .httpBasic();
		http.logout().logoutUrl("/logout")
		.logoutSuccessUrl("/logout/success")
		.addLogoutHandler(logoutHandler);		
	}

}
