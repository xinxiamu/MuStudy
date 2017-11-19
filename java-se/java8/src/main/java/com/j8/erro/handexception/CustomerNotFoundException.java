package com.j8.erro.handexception;

public class CustomerNotFoundException extends RuntimeException{
	public CustomerNotFoundException(String message){
		super(message);
	}
}