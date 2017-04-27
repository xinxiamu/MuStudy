package com.ymu.infrastructure.mq.rabbitmq;

/**
 * 
 * 队列名称。在注解中可用。
 * 
 * @author mutou
 */
public interface QueueName {
	
	final static String MAIN_QUEUE = "main.queue";
	final static String TMP_QUEUE = "tmp.queue";
	final static String TOPIC_MAIN_QUEUE = "topic.main.queue";
	final static String TOPIC_TMP_QUEUE = "topic.tmp.queue";
}
