package com.weifan.ferrier.springboot.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.weifan.ferrier.springboot.security.logout.MyLogoutHandler;
import com.weifan.ferrier.springboot.security.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableWebSecurity
public class HttpSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private MyLogoutHandler logoutHandler;
	
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;
	
	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		log.info("====================================" + authenticationSuccessHandler);
		log.info("====================================" + authenticationFailureHandler);
		
		http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
		.anyRequest().permitAll()
		.and()
        .formLogin()
        .loginPage("/login")
        .loginProcessingUrl("/login")
        .usernameParameter("username")
        .passwordParameter("password")
        .failureHandler(authenticationFailureHandler)
        .failureUrl("/login?error=true")
        .and()
        .logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
		.logoutSuccessUrl("/logout/success")
		.addLogoutHandler(logoutHandler);
		
	}
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService createUserDetailsService() {
		return new MyUserDetailsService();
	}
	
	public  class MyUserDetailsService implements UserDetailsService{
		
		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			var user = userRepository.findByUsername(username);
			log.info("user : " + user);
			return user;
		}
		
	}

}
