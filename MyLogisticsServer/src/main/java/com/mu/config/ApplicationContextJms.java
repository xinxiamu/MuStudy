package com.mu.config;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.SimpleMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;

import com.mu.logistics.messages.ListenerImpl;

/**
 * 配置文件。配置jms。使用activemq.
 * 
 */
//@Configuration
//@ComponentScan
public class ApplicationContextJms {

	static String mailboxDestination = "mailbox-destination";

	@Bean
	ConnectionFactory connectionFactory() {
		return new CachingConnectionFactory(new ActiveMQConnectionFactory(
				"tcp://localhost:61616"));
	}

	@Bean
	MessageListenerAdapter receiver() {
		return new MessageListenerAdapter(new ListenerImpl()) {
			{
				setDefaultListenerMethod("listen");
			}
		};
	}

	@Bean
	SimpleMessageListenerContainer container(
			final MessageListenerAdapter messageListener,
			final ConnectionFactory connectionFactory) {
		return new SimpleMessageListenerContainer() {
			{
				setMessageListener(messageListener);
				setConnectionFactory(connectionFactory);
				setDestinationName(mailboxDestination);
			}
		};
	}

	@Bean
	JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
		return new JmsTemplate(connectionFactory);
	}
}
