 package com.example.employee.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.employee.security.EmployeeLoginDetails;
import com.example.employee.security.EmployeeServiceDetails;

@Component
public class Jwtfilter extends OncePerRequestFilter{

	@Autowired
	public Jwtutils utils;
		
	@Autowired
	public EmployeeServiceDetails service;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String header=request.getHeader("Authorization");
		String token=null;
		String user=null;
		if(header!=null && header.contains("Bearer")) {
			token = header.substring(6);
			user = utils.getUsername(token);
		}
		
		if(user!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			
			EmployeeLoginDetails currentuser= (EmployeeLoginDetails) service.loadUserByUsername(user);
			
			if(utils.isTokenValidate(token, currentuser)) {
				
				UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(currentuser,
						null,currentuser.getAuthorities());
				userToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(userToken);
				
			}
			
		}
		filterChain.doFilter(request, response);
		
	}

	
}
