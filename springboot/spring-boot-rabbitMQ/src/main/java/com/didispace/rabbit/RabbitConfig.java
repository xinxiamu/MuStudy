package com.didispace.rabbit;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 翟永超
 * @create 2016/9/25.
 * @blog http://blog.didispace.com
 */
@Configuration
@EnableRabbit
public class RabbitConfig {

    @Bean
    public Queue helloQueue() {
        return new Queue("hello",true);	//队列持久化true
    }
    
    @Bean
    public Queue muQu() {
		return new Queue("mu");
	}
    
    @Bean
    public Queue mainQ() {
		return new Queue("main.queue");
	}

}
