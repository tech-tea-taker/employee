package com.example.employee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.Id;

@Entity
@Table(name="emp")
public class employeemodel {
	
	public String toString() {
		return "employeemodel [id=" + id + ", name=" + name + ", email=" + email + ", phone_no=" + phone_no + "]";
	}
	@Id
    private String id;
	@Column(name="name")
    private String name;
	@Column(name="email")
    private String email;
	@Column(name="phone_no")
    private String phone_no;
	
    
    public employeemodel() {
    	
    }
    
	public employeemodel(String name, String email, String phone_no) {
		super();
		this.name = name;
		this.email = email;
		this.phone_no = phone_no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone_no() {
		return phone_no;
	}
	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}
    
    

}
