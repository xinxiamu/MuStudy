package com.terran4j.springboot.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloApp implements ApplicationRunner {
	
	@Autowired
	private HelloService helloService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		String msg = helloService.hello("Spring Boot");
		System.out.println(msg);
	}

	public static void main(String[] args) {
		SpringApplication.run(HelloApp.class, args);
	}

}
