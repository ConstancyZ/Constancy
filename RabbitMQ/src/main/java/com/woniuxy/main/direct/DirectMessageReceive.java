package com.woniuxy.main.direct;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues="hello") //监听消息队列
public class DirectMessageReceive {
	@RabbitHandler  //让这个方法成为处理消息的方法
	public void receive(String message) {
		System.out.println("消费者A接收到:"+message);
	}
}
