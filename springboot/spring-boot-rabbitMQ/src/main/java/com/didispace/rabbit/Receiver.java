package com.didispace.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author 翟永超
 * @create 2016/9/25.
 * @blog http://blog.didispace.com
 */
@Component
public class Receiver {

    @RabbitHandler
    @RabbitListener(queues = "hello")
    public void process(String hello) {   
        System.out.println("Receiver============ : " + hello);
    }
    
    @RabbitHandler
    @RabbitListener(queues = "mu")
    public void muGet(String mu) {
		System.out.println("Receiver-------------:" + mu);
	}
    
    @RabbitHandler
    @RabbitListener(queues = "main.queue")
    public void mainQGet(String mainContext) {
		System.out.println("Receiver-------------:" + mainContext);
	}

}
