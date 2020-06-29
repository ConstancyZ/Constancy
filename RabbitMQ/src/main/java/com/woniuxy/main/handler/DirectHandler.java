package com.woniuxy.main.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.woniuxy.main.direct.DirectMessageSender;

@RestController
@RequestMapping("/direct")
public class DirectHandler {
	
	@Autowired
	private DirectMessageSender sender;
	
	@RequestMapping("/send")
	public String send() {
		sender.send();
		return "success";
	}
}
