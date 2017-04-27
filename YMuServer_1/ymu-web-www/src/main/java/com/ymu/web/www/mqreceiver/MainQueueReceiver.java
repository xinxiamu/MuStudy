package com.ymu.web.www.mqreceiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.ymu.infrastructure.mq.rabbitmq.QueueName;

@Component
@RabbitListener(queues = QueueName.MAIN_QUEUE)
public class MainQueueReceiver {
	
	public static String mainQMsg;

	@RabbitHandler
    public void receiveMainQMsg(String context) {
        System.out.println("Receiver mainQ : " + context);
        mainQMsg = context;
    }
}
