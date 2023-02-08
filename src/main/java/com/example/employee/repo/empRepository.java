package com.example.employee.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employee.model.employeemodel;


public interface empRepository extends JpaRepository<employeemodel, String> {

	public List<employeemodel> findAllByName(String name);
	public List<employeemodel> findAllByEmail(String email);
//	public List<employeemodel> findAllByPhoneno(String phone_no);
	public employeemodel findByEmail(String email);

}
