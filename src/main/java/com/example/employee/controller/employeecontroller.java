package com.example.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.model.employeemodel;
import com.example.employee.service.services;

@CrossOrigin("http://localhost:4200")

@RestController
@RequestMapping("/employee/details")
public class employeecontroller {
	
	@Autowired
	private services services;

	@GetMapping("/allemp")
	public ResponseEntity<List<employeemodel>> allemployee(){
		List<employeemodel> emp= services.findAll();
		
		return new ResponseEntity<>(emp,HttpStatus.OK);
	}
	
	@PostMapping("/createEmployee")
	public ResponseEntity<String> createEmployee(@RequestBody employeemodel empmodel){
		employeemodel emp= services.addEmployee(empmodel);
		if(emp !=null) {
		return new ResponseEntity<>("added",HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>("null",HttpStatus.CREATED);
		}
	}
	
	@PutMapping("/updateEmployee")
	public ResponseEntity<employeemodel> updateEmployee(@RequestBody employeemodel empmodel){
		employeemodel updateempmodel=services.updateEmployee(empmodel);
		return new ResponseEntity<>(updateempmodel,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable("id") String id) {
		services.deleteEmployee(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/findEmployee/{name}")
	public ResponseEntity<List<employeemodel>> findAEmployee(@PathVariable("name") String name){
		List<employeemodel> employees = services.findAllEmployee(name);
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}
	
	@GetMapping("/error")
	public ResponseEntity<String> Error(){
		return new ResponseEntity<>("Error",HttpStatus.OK);
	}
	
	
	
}
