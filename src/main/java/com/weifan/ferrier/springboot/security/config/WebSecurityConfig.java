package com.weifan.ferrier.springboot.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.weifan.ferrier.springboot.security.repository.SecUserRepository;

@EnableWebSecurity
public class WebSecurityConfig implements WebMvcConfigurer {
	
	
	@Autowired
	private SecUserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService() throws Exception {
		
		return new FPUserDetailsService();
		
	}
	
	public  class FPUserDetailsService implements UserDetailsService{
		
		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			userRepository.findByUsername(username);
			return new User("user", passwordEncoder.encode("123456"),
					java.util.List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));
		}
		
	}
	
}