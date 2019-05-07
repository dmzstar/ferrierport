package com.weifan.ferrier.mobile.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.weifan.ferrier.springboot.mvc.COCViewController.CocHandlerInterceptor;

@Configuration
public class MobileMvcConfig implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new CocHandlerInterceptor()).addPathPatterns("/mobile/**");
		WebMvcConfigurer.super.addInterceptors(registry);
	}

}
