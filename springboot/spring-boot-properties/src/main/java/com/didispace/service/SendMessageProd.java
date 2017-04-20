package com.didispace.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class SendMessageProd implements SendMessage {

	@Value("${server.port}")
	private String serverPort;

	@Override
	public String send() {
		System.out.println("-------dev-serverPort==" + serverPort);
		return serverPort;  
	}  

}
