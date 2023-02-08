package com.example.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.employee.jwt.Jwtutils;
import com.example.employee.model.employeeloginmodel;
import com.example.employee.repo.EmployeeLoginRepository;
import com.example.employee.security.EmployeeLoginDetails;
import com.example.employee.security.EmployeeServiceDetails;

@Service
public class EmployeeLoginService {
	
	@Autowired
	public EmployeeLoginRepository emploginrepo;
	
	@Autowired
	public AuthenticationManager authmanager;
	
	@Autowired
	public Jwtutils utils;
	
	@Autowired
	public EmployeeServiceDetails servicedetails;

	public String RegistrationEmployee(employeeloginmodel model) {
		if(emploginrepo.findById(model.getEmail()).isEmpty()) {
		try {
			BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
			String encodedPassword=encoder.encode(model.getPassword());
			model.setPassword(encodedPassword);
			emploginrepo.save(model);
			
			return "Employee Added.";
			
		}catch(Exception e){
			
			return e.getMessage();
		}
		}else {
			return "email already in use.";
		}
	}
	
	public String AuthenticationEmployee(employeeloginmodel model) {
		
		try {
			Authentication auth=authmanager.authenticate
					(new UsernamePasswordAuthenticationToken(model.getEmail(), model.getPassword()));
			
			if(auth.isAuthenticated()) {
				EmployeeLoginDetails deatils=new EmployeeLoginDetails(model);
				String token=utils.generateToken(deatils);
				return token;
			}
			
			return "User not Authenticated";
			
		}catch(Exception e) {
			
			if(emploginrepo.findById(model.getEmail()).isPresent()) {
				employeeloginmodel mdl=emploginrepo.findById(model.getEmail()).get();
				
				if(!mdl.getPassword().equals(model.getPassword())) {
					return "email or password does not match";
				}
			
			}
		return "there is an error";
		}
	

	}
	
	public Boolean validateToken(String token) {
		
		try {
		String username=utils.getUsername(token);
		EmployeeLoginDetails employeeLoginDetails=(EmployeeLoginDetails) servicedetails.loadUserByUsername(username);
				
		return utils.isTokenValidate(token,employeeLoginDetails);
		}catch(Exception e) {
			return false;
		}
	}
}









