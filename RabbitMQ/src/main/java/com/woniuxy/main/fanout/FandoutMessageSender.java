package com.woniuxy.main.fanout;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FandoutMessageSender {
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	public void send() {
		String message = "分发模式的消息";
		//参数1：交换机的名字
		//参数2：指定模式（默认/路由）
		//参数3：发送的数据
		amqpTemplate.convertAndSend("fanoutExchange", "", message);
	}
}
