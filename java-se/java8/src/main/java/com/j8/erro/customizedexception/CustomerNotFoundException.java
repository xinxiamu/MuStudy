package com.j8.erro.customizedexception;

/**
 * 运行时异常。UnCheckedException。不需要捕捉
 */
public class CustomerNotFoundException extends RuntimeException{
	public CustomerNotFoundException(String message){
		super(message);
	}
}