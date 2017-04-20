package com.didispace.service;

import org.springframework.stereotype.Service;

import com.didispace.exception.MyException;

@Service
public class UserService {

	public String getUserName(int b) throws Exception {
		if (b == 0) {
			throw new Exception("系统内部异常，稍后再试");
		} else if (b == 1) {
			throw new MyException("抛异常MyException");
		}
		return "统一异常处理";
	}
}
