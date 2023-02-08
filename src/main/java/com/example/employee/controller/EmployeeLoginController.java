package com.example.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.jwt.Jwtutils;
import com.example.employee.model.employeeloginmodel;
import com.example.employee.service.EmployeeLoginService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/employee/auth")
public class EmployeeLoginController {
	
	@Autowired
	public EmployeeLoginService service;
	
	
	public Jwtutils utils;

	@PostMapping("/registration")
	public ResponseEntity<String> getRegistration(@RequestBody employeeloginmodel model ){
		
		String res=service.RegistrationEmployee(model);
		
		return new ResponseEntity<>(res,HttpStatus.CREATED);
		
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<String> getAuthentication(@RequestBody employeeloginmodel model){
		
		String token=service.AuthenticationEmployee(model);
		
		return new ResponseEntity<>(token,HttpStatus.OK);
		
	}
	
	@GetMapping("/tokenValidation/{token}")
	public Boolean getTokenValidation(@PathVariable("token") String token ) {
		Boolean vt=service.validateToken(token);
		return vt;
		
	} 
	
}
