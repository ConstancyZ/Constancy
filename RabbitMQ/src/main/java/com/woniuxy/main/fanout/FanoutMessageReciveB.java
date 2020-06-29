package com.woniuxy.main.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues="fanout.B")
public class FanoutMessageReciveB {
	
	@RabbitHandler
	public void recive(String message) {
		System.out.println("消费者B收到:"+message);
	}
}
