package com.woniuxy.main.topic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.woniuxy.main.pojo.Product;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicProductSender {

	@Autowired
	private AmqpTemplate amqpTemplate;
	
	public void send(Product product) throws JsonProcessingException { amqpTemplate.convertAndSend("product.exchange", "product",new ObjectMapper().writeValueAsString(product));
	}
}
