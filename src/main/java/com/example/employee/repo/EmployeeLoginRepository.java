package com.example.employee.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employee.model.employeeloginmodel;

public interface EmployeeLoginRepository extends JpaRepository<employeeloginmodel, String>{

}
