package com.j8.erro.customizedexception;

/**
 * 自定义异常。CheckException
 */
public class InValidInfoException extends Exception{
	public InValidInfoException(String message){
		super(message);
	}
}