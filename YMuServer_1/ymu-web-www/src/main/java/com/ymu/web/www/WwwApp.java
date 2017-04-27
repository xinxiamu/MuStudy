package com.ymu.web.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ymu"})
@ImportResource({"classpath:ymu-dubbo.xml"})
@ServletComponentScan
public class WwwApp {

	public static void main(String[] args) {

		SpringApplication.run(WwwApp.class, args);

	}

}
