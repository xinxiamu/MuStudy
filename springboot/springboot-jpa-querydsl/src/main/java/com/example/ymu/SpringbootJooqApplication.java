package com.example.ymu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class SpringbootJooqApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJooqApplication.class, args);
	}
	
}
