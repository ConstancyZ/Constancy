package com.woniuxy.main.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.woniuxy.main.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.woniuxy.main.topic.TopicProductSender;

@RestController
@RequestMapping("/product")
public class ProductHandler {

	@Autowired
	private TopicProductSender sender;
	
	@RequestMapping("/find")
	public String find() throws JsonProcessingException {
		Product product=new Product();
		product.setPid(1001);
		product.setPname("csd");
		product.setCount(20);
		product.setPrice(12.00);
		sender.send(product);
		
		return "success";
	}
}
