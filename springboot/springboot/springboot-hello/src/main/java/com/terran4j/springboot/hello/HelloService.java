package com.terran4j.springboot.hello;

import org.springframework.stereotype.Component;

@Component
public class HelloService {

	public String hello(String name) {
		return "Hello, " + name + "!";
	}
	
}
