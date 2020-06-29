package com.woniuxy.main.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.woniuxy.main.pojo.Product;

@RestController
@RequestMapping("/temp")
public class RestTemplateHandler {
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/find")
	public Product find() {
		String url = "http://127.0.0.1:8081/product/findProductById?pid=1001";
		Product products = restTemplate.getForObject(url, Product.class);
		return products;
	}
}
