package com.weifan.ferrier.cms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.weifan.ferrier.springboot.mvc.COCViewController.CocHandlerInterceptor;

@Configuration
public class CmsMvcConfig implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new CocHandlerInterceptor()).addPathPatterns("/cms/**");
		WebMvcConfigurer.super.addInterceptors(registry);
	}
	
	
}
