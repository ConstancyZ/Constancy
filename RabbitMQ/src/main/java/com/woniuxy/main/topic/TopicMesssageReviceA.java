package com.woniuxy.main.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues="topic.A")
public class TopicMesssageReviceA {
	@RabbitHandler
	public void revice(String message) {
		System.out.println("topic消费者A接收到:"+message);
	}
}
