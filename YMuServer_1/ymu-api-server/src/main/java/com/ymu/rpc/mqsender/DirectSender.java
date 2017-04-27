package com.ymu.rpc.mqsender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ymu.infrastructure.mq.rabbitmq.QueueName;

/**
 * ｄefault模式发送消息。
 * 
 * @author mutou
 *
 */
@Component
public class DirectSender {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void sendToMainＱ(String context) {
		System.out.println("Sender : " + context);
		this.rabbitTemplate.convertAndSend(QueueName.MAIN_QUEUE, context);
	}
	
	public void sendToMainＱ(Object context) {
		System.out.println("Sender : " + context);
		this.rabbitTemplate.convertAndSend(QueueName.MAIN_QUEUE, context);
	}
}
