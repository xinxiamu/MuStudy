package com.didispace.rabbit;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void send() {
		String context = "hello================ " + new Date();
		System.out.println("Sender : " + context);
		this.rabbitTemplate.convertAndSend("hello", context);
	}
	
	public void sendMumu() {
		String context = "木头";
		System.out.println("=============Sender_mumu:" + context);
		this.rabbitTemplate.convertAndSend("mu", context);  	
	}
	
	public void sendMainQ() {
		String context = "木头";
		System.out.println("=============Sender_mainQ:" + context);
		this.rabbitTemplate.convertAndSend("main.queue", context);  	
	}

}