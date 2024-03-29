package com.ymu.rpc;

import java.util.concurrent.CountDownLatch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ymu"})
@ImportResource({"classpath:ymu-dubbo.xml"})
public class ApiApp {

	private static final Logger logger = LogManager.getLogger(ApiApp.class);

	@Bean
	public CountDownLatch closeLatch() {
		return new CountDownLatch(1);
	}

	public static void main(String[] args) throws InterruptedException {
		ApplicationContext ctx = SpringApplication.run(ApiApp.class, args);
		logger.info("项目启动!");
		CountDownLatch closeLatch = ctx.getBean(CountDownLatch.class);
		closeLatch.await();
	}

}
