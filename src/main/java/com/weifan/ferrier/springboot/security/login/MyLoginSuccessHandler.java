package com.weifan.ferrier.springboot.security.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MyLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Autowired
	private DetermineUrlHandler determineUrlHandler;

	@Override
	protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
		var user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		log.info("Principal : " + user);
		var result = determineUrlHandler.determine(user);
		if (result.isSuccess()) {
			return result.getUrl();
		}
		return super.determineTargetUrl(request, response);
	}

	public static boolean hasRole(UserDetails user, String role) {
		for (var a : user.getAuthorities()) {
			if (a.getAuthority().equals(role)) {
				return true;
			}
		}
		return false;
	}

	public interface DetermineUrlHandler {

		default public Result determine(UserDetails user) {
			return new Result();
		}

		@Data
		public static class Result {

			private boolean success = true;
			private String url;

		}

	}

}
