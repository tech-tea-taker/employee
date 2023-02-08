package com.example.employee.jwt;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.example.employee.security.EmployeeLoginDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class Jwtutils implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final long expireTime=24*60*60*1000;
	private final String Secret_key="-JaNdRgUkXp2s5u8x/A?D(G+KbPeShVmYq3t6w9y$B&E)H@McQfTjWnZr4u7x!A%";

	public String generateToken(EmployeeLoginDetails employeelogindetails) {
		 Map<String,Object> claim=new HashMap<>();
		
		return Jwts.builder().setClaims(claim)
				.setSubject(employeelogindetails
						.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+ expireTime))
				.signWith(SignatureAlgorithm.HS512, Secret_key).compact();
			
	}
	
	private Claims getAllclaims(String token) {
		
		return Jwts.parser().setSigningKey(Secret_key).parseClaimsJws(token).getBody();
	}
	
	public String getUsername(String token) {
		
		return getAllclaims(token).getSubject();
	}
	
	public Boolean isTokenValidate(String token , EmployeeLoginDetails empdetail) {
		Boolean token_validation=getAllclaims(token).getExpiration().before(new Date());
		String username=getUsername(token);
		return (username.equals(empdetail.getUsername()) && !token_validation);
	}
	
}
