package com.ymu.infrastructure.mq.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq配置。
 *　自动配置。
 * @author mutou
 *
 */
@Configuration
@EnableRabbit
public class RabbitConfig {
	
	/**
	 * 定义队列。
	 * @return	
	 */
    @Bean
    public Queue mainQueue() {
        return new Queue(QueueName.MAIN_QUEUE,true);	//队列持久化true
    }
    
    @Bean
    public Queue tmpQueue() {
		return new Queue(QueueName.TMP_QUEUE);	//队列不持久化
	}
    
    

}
