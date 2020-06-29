package com.woniuxy.main.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues="topic.B")
public class TopicMesssageReviceB {
	@RabbitHandler
	public void revice(String message) {
		System.out.println("topic消费者B接收到:"+message);
	}
}
