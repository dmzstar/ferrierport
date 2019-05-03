package com.weifan.ferrier.springboot.core;

import org.springframework.context.ApplicationContext;

public class Beans {
	
	public static ApplicationContext context;
	
	public static <T> T repository(Class<T> clazz) {
		return context.getBean(clazz);
	}
	
	public static <T> T get(Class<T> clazz) {
		return context.getBean(clazz);
	}

}
