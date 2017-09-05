package com.terran4j.springboot.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(HelloWebMvcConfigurer.class)
@SpringBootApplication
public class HelloWebApp {

	public static void main(String[] args) {
		SpringApplication.run(HelloWebApp.class, args);
	}

}
