package com.woniuxy.main.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.woniuxy.main.fanout.FandoutMessageSender;

@RestController
@RequestMapping("/fanout")
public class FanoutHandler {
	@Autowired
	private FandoutMessageSender sender;
	
	@RequestMapping("/send")
	public String send() {
		sender.send();
		return "success";
	}
}
