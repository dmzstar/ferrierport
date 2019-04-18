package com.weifan.ferrier.springboot.security.config;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.weifan.ferrier.springboot.security.repository.SecUserRepository;

@Configurable
public class SecurityBeanConfig {
	
	@Autowired
	private SecUserRepository userRepository;
	
	public UserDetailsService getUserDetailsService() {
		return new FPUserDetailsService();
	}
	
	
	public  class FPUserDetailsService implements UserDetailsService{
		
		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			userRepository.findByUsername(username);
			return new User("", "", new ArrayList<>());
		}
		
	}
	

}
