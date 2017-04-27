package com.ymu.infrastructure.mq.rabbitmq;

/**
 *  exchange交换机种类。
 * 	Direct(默认模式)：direct 类型的行为是"先匹配, 再投送". 即在绑定时设定一个 routing_key, 消息的routing_key 匹配时, 才会被交换器投送到绑定的队列中去.
 *	Topic：按规则转发消息（最灵活）
 *	Headers：设置header attribute参数类型的交换机
 *	Fanout：转发消息到所有绑定队列
 * @author mutou
 *
 */
public enum ExchangeEnum {
	
	TOPIC_MAIN_EXCHANGE("topic.main_exchange"),
	TOPIC_TMP_EXCHANGE("topic.tmp_exchange"),
	DIRECT_MAIN_EXCHANGE("direct.main_exchange"),
	DIRECT_TMP_EXCHANGE("direct.tmp_exchange"),
	HEADERS_MAIN_EXCHANGE("headers.main_exchange"),
	HEADERS_TMP_EXCHANGE("headers.tmp_exchange"),
	FANOUT_MAIN_EXCHANGE("fanout.main_exchange"),
	FANOUT_TMP_EXCHANGE("fanout.tmp_exchange");
	
	private final String exchangeName;

	ExchangeEnum(String exchangeName){
		this.exchangeName = exchangeName;
	}
	
	public String getExchangeName() {
		return exchangeName;
	}
}
