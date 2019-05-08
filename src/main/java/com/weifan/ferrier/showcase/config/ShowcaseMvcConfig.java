package com.weifan.ferrier.showcase.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.weifan.ferrier.springboot.mvc.COCViewController.CocHandlerInterceptor;

@Configuration
public class ShowcaseMvcConfig implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new CocHandlerInterceptor()).addPathPatterns("/showcase/**");
		WebMvcConfigurer.super.addInterceptors(registry);
	}
	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//registry.addResourceHandler("/showcase/bootstrap4/**").addResourceLocations("classpath:/templates/showcase/bootstrap4/");
		registry.addResourceHandler("/showcase/**").addResourceLocations("classpath:/templates/showcase/");
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}
	
	
}
