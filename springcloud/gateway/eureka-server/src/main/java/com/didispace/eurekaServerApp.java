package com.didispace;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class eurekaServerApp {

	public static void main(String[] args) {
		new SpringApplicationBuilder(eurekaServerApp.class).web(true).run(args);
	}

}
