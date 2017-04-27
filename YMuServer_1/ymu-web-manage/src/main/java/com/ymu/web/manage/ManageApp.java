package com.ymu.web.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ymu"})
@ImportResource({"classpath:ymu-dubbo.xml"})
@ServletComponentScan
public class ManageApp {

	public static void main(String[] args) {

		SpringApplication.run(ManageApp.class, args);

	}

}
