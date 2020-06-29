package com.woniuxy.main.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.woniuxy.main.topic.TopicMessageSender;

@RestController
@RequestMapping("/topic")
public class TopicHandler {
	@Autowired
	private TopicMessageSender sender;
	
	@RequestMapping("/send")
	public String send() {
		sender.send();
		return "success";
	}
}
