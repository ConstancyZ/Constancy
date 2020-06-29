package com.woniuxy.main.topic;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicMessageSender {
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	public void send() {
		String message = "tipic模式的消息";
		amqpTemplate.convertAndSend("topic.exchange", "abc.abc", message);
	}
}
