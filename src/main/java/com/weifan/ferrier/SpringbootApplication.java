package com.weifan.ferrier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableSpringConfigured
@SpringBootApplication
@ComponentScan(basePackages={"com.weifan.ferrier"})
@EnableJpaRepositories(basePackages = {"com.weifan.ferrier"})
public class SpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

}
