package com.ymu.infrastructure.mq.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq配置。 　自动配置。
 * 
 * @author mutou
 *
 */
@Configuration
public class TopicRabbitConfig {

	/**
	 * 初始化topic队列。
	 * 
	 * @return
	 */
	@Bean
	public Queue topicMainQueue() {
		return new Queue(QueueName.TOPIC_MAIN_QUEUE);
	}

	@Bean
	public Queue topicTmpQueue() {
		return new Queue(QueueName.TOPIC_TMP_QUEUE);
	}

	/**
	 * 初始化交换机topic.main.exchange
	 * 
	 * @return
	 */
	@Bean
	TopicExchange topicMainExchange() {
		return new TopicExchange(
				ExchangeEnum.TOPIC_MAIN_EXCHANGE.getExchangeName());
	}

	@Bean
	TopicExchange topicTmpExchange() {
		return new TopicExchange(
				ExchangeEnum.TOPIC_TMP_EXCHANGE.getExchangeName());
	}

	/**
	 * topicMainQueue绑定到交换机topicMainExchange； 路由键：topic.main.queue
	 * {@link QueueEnum}
	 * 即：当交换机topicMainExchange收到路由键为topic.main.queue的消息时，
	 * 交换机将把消息转发给队列topicMainQueue；
	 * 
	 * @param topicMainQueue
	 * @param topicMainExchange
	 * @return
	 */
	@Bean
	Binding bindingTopicMainEgTopicMainQ(Queue topicMainQueue,
			TopicExchange topicMainExchange) {
		return BindingBuilder.bind(topicMainQueue).to(topicMainExchange)
				.with(QueueName.TOPIC_MAIN_QUEUE);
	}

	/**
	 * topicTmpQueue绑定到交换机topicMainExchange； 路由键：topic.#。
	 * 即：交换机收到topic.#的消息将转发给队列topicTmpQueue
	 * 
	 * @param topicTmpQueue
	 * @param topicMainExchange
	 * @return
	 */
	@Bean
	Binding bindingTopicMainEgTopicTmpQ(Queue topicTmpQueue,
			TopicExchange topicMainExchange) {
		return BindingBuilder.bind(topicTmpQueue).to(topicMainExchange)
				.with("topic.#");
	}

	/**
	 * topicTmpQueue绑定到交换机topicTmpExchange。 路由键：topic.tmp.queue
	 * 即：交换机收到键为topic.tmp.queue的消息将转发给队列topicTmpQueue，否者丢弃。
	 * 
	 * @param topicTmpQueue
	 * @param topicTmpExchange
	 * @return
	 */
	@Bean
	Binding bindingTopicTmpEgTopicTmpQ(Queue topicTmpQueue,
			TopicExchange topicTmpExchange) {
		return BindingBuilder.bind(topicTmpQueue).to(topicTmpExchange)
				.with(QueueName.TOPIC_TMP_QUEUE);
	}
}
