package com.woniuxy.main.direct;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DirectMessageSender {
	@Autowired
	private AmqpTemplate amqpTemplate; //用于发消息的工具
	
	//负责向消息队列发送消息
	public void send() {
		String message = "hello world!"; //要发的数据
		//参数1：消息队列
		//参数2：要发送的数据
		amqpTemplate.convertAndSend("hello", message);
	}
}
