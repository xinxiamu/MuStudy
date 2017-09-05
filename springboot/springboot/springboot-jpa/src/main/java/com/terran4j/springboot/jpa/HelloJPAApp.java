package com.terran4j.springboot.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(HelloWebMvcConfigurer.class)
@SpringBootApplication
public class HelloJPAApp {

	public static void main(String[] args) {
		SpringApplication.run(HelloJPAApp.class, args);
	}

}
