package com.example.employee.exception;

public class UserNotFoundException extends RuntimeException{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String s) {
		super(s);
	}
}
