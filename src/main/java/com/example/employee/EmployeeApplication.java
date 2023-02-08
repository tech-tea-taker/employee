package com.example.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class EmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
	}
	
	@Bean
	 WebMvcConfigurer CorsConfigurer() {
	   return new WebMvcConfigurer() {

	      @Override
	      public void addCorsMappings(CorsRegistry registry) {
	         // TODO Auto-generated method stub
	         WebMvcConfigurer.super.addCorsMappings(registry);
	         registry.addMapping("/**").allowedOrigins("http://localhost:4200").allowedMethods("*").maxAge(3600L)
	               .allowedHeaders("*").exposedHeaders("Authorization").allowCredentials(true);
	      }

	   };
	}

}
