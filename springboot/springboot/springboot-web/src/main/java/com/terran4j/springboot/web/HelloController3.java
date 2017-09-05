package com.terran4j.springboot.web;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController3 {
	
	private static final Logger log = LoggerFactory.getLogger(HelloController3.class);

	@Autowired
	private HelloService helloService;
	
	// URL示例：  http://localhost:8080/hello3?name=terran4j
	@RequestMapping(value = "/hello3", method = RequestMethod.GET)
	public HelloBean sayHello(@RequestParam("name") String name) {
		HelloBean hello = new HelloBean();
		hello.setName(name);
		hello.setMessage(helloService.hello(name));
		hello.setTime(new Date());
		if (log.isInfoEnabled()) {
			log.info("hello bean is: {}", hello);
		}
		return hello;
	}
	
}
