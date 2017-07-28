package com.example.ymu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ServletComponentScan
//@ComponentScan(basePackages={"com.ymu.framework.dao"})
public class SpringbootJooqApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJooqApplication.class, args);
	}
	
}
