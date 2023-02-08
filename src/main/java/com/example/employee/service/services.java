package com.example.employee.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employee.model.employeemodel;
import com.example.employee.repo.empRepository;

@Service
public class services {

	@Autowired
	private empRepository emprepo;
	
	public List<employeemodel> findAll(){
		return emprepo.findAll();
		
	}
	
	public employeemodel addEmployee(employeemodel empmodel) {
		try { 
		emprepo.findByEmail(empmodel.getEmail()).getEmail();
		
		return null;
		
		}catch(Exception e) {
		
		
			empmodel.setId(UUID.randomUUID().toString());
			return emprepo.save(empmodel);
		}
	}
	
	public employeemodel updateEmployee(employeemodel empmodel) {
		return emprepo.save(empmodel);
	}
	
	public String deleteEmployee(String id) {
		 emprepo.deleteById(id);
		 return "Ok";
	}
	
	public List<employeemodel> findAllEmployee(String name) {
      if(!emprepo.findAllByName(name).isEmpty()) {
    	  return emprepo.findAllByName(name);
      }
      else if(!emprepo.findAllByEmail(name).isEmpty()) {
    	  return emprepo.findAllByEmail(name);
//      }else if(emprepo.findAllByPhoneNo(name).isEmpty()){
//    	  return emprepo.findAllByPhoneNo(name);
      }else {
    	  return null;
      }
	}
		
	}



