package com.weifan.ferrier.springboot.security.login;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.weifan.ferrier.springboot.security.login.MyLoginSuccessHandler.DetermineUrlHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public  class ByRoleHandler implements DetermineUrlHandler {

	@Autowired
	private Environment env;

	@Override
	public Result determine(UserDetails user) {
		var result = new Result();
		result.setSuccess(true);
		result.setUrl(determineUrl(user.getAuthorities()));
		return result;
	}

	public String determineRole(Collection<? extends GrantedAuthority> list) {
		if (list != null && !list.isEmpty()) {
			var roleName = ((GrantedAuthority) list.toArray()[0]).getAuthority();
			return roleName;
		}
		return "";
	}

	public String determineUrl(Collection<? extends GrantedAuthority> list) {
		String roleName = determineRole(list);
		log.info(env + "sssssssssss");
		var url = env.getProperty("security.loginsuccess.type.byRole." + roleName);
		return url;
	}

}
