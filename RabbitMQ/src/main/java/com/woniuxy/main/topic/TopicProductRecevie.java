package com.woniuxy.main.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.woniuxy.main.pojo.Product;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@RabbitListener(queues="result.queue")
public class TopicProductRecevie {

	@RabbitHandler
	public void recevie(String product) throws JsonMappingException, JsonProcessingException {
		System.out.println("用户模块接收到:"+product);
		System.out.println(product.getClass());
		//System.out.println(new ObjectMapper().readValue(product, Product.class));
	}
}
