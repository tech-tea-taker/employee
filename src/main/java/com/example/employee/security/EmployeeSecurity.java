package com.example.employee.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.employee.jwt.Jwtfilter;

@EnableWebSecurity
@Configuration
public class EmployeeSecurity {

	@Autowired
	public EmployeeServiceDetails serviceDetails;
	
	@Autowired
	public Jwtfilter filter;
	
	@Bean
	 AuthenticationProvider authenticateprovider() {
		
		DaoAuthenticationProvider authprovider= new DaoAuthenticationProvider();
		authprovider.setUserDetailsService(serviceDetails);
		authprovider.setPasswordEncoder(new BCryptPasswordEncoder());
		return authprovider;
		
	}
	
	@Bean
	SecurityFilterChain securityfilterchain(HttpSecurity http) throws Exception {
		
		http.csrf().disable().cors().disable().authorizeHttpRequests()
		.antMatchers("/employee/auth/registration",
				"/employee/auth/authenticate",
				"/employee/auth//tokenValidation/{token}",
				"/employee/details/allemp")
		.permitAll()
		.antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
		.anyRequest().authenticated()
		.and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
		
		
		
	}
	
	
	@Bean
	AuthenticationManager authmanager(AuthenticationConfiguration config) throws Exception {
		
		return config.getAuthenticationManager();
	}
	
	
}
