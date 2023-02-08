package com.example.employee.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.employee.model.employeeloginmodel;
import com.example.employee.repo.EmployeeLoginRepository;
@Service
public class EmployeeServiceDetails implements UserDetailsService{
	
	@Autowired
	public EmployeeLoginRepository emploginrepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		employeeloginmodel details= emploginrepository.findById(username).get();
		
		return new EmployeeLoginDetails(details);
	}

}
