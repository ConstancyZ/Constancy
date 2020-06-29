package com.woniuxy.main.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues="fanout.A")
public class FanoutMessageReciveA {
	
	@RabbitHandler
	public void recive(String message) {
		System.out.println("消费者A收到:"+message);
	}
}
