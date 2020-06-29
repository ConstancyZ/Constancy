package com.woniuxy.main.configuration;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mr Zay
 * @time 2020.05.24
 */
@Configuration
@Slf4j
public class RabbitMQConfiguration {
	Logger logger = LoggerFactory.getLogger(RabbitMQConfiguration.class);
	/*
	 * direct
	 */
	//配置消息队列
	@Bean
	public Queue hello() {
		return new Queue("hello");//指定消息队列的名字
	}


	/*
	 * fanout模式：分发模式
	 */
	
	@Bean
	public Queue fanoutQueueA() {
		return new Queue("fanout.A");
	}
	@Bean
	public Queue fanoutQueueB() {
		return new Queue("fanout.B");
	}
	//交换机
	@Bean
	public FanoutExchange getFanoutExchange(){
		return new FanoutExchange("fanoutExchange");
	}
	//绑定交换机与队列
	@Bean
	public Binding bindingExchangeA(Queue fanoutQueueA,FanoutExchange exchange){
		return BindingBuilder.bind(fanoutQueueA).to(exchange);
	}
	@Bean
	public Binding bindingExchangeB(Queue fanoutQueueB,FanoutExchange exchange){
		return BindingBuilder.bind(fanoutQueueB).to(exchange);
	}
	
	/*
	 * topic 主题模式
	 */
	@Bean
	public Queue topicQueueA() {
		return new Queue("topic.A");
	}
	@Bean
	public Queue topicQueueB() {
		return new Queue("topic.B");
	}
	//交换机
	@Bean
	public TopicExchange createTopicExchange() {
		return new TopicExchange("topic.exchange");
	}
	//绑定
	@Bean
	public Binding bindingTopicA(Queue topicQueueA,TopicExchange createTopicExchange) {
		return BindingBuilder.bind(topicQueueA).to(createTopicExchange).with("abc.#");//指定路由
	}
	@Bean
	public Binding bindingTopicB(Queue topicQueueB,TopicExchange createTopicExchange) {
		return BindingBuilder.bind(topicQueueB).to(createTopicExchange).with("#.abc");//指定路由
	}
	
	/*
	 * 用户模块发送消息需要的消息队列、交换机
	 */
	@Bean
	public Queue productQueue() {
		return new Queue("result.queue");
	}
	@Bean
	public TopicExchange productExchange() {
		return new TopicExchange("product.exchange");
	}
	@Bean
	public Binding bindingProduceQueueAndExchange(Queue productQueue,TopicExchange productExchange) {
		return BindingBuilder.bind(productQueue).to(productExchange).with("product");
	}

	/**
	 * 延迟消息队列配置
	 */
	@Bean
	public Queue queue() {
		Queue queue = new Queue("test_queue_1",true);
		return queue;
	}

	@Bean
	public CustomExchange delayExchange() {
		Map<String, Object> args = new HashMap<>();
		args.put("x-delayed-type","direct");
		return new CustomExchange("test_exchange","x-delayed-message",true, false,args);
	}
	// 绑定队列和交换机
	@Bean
	public  Binding binding() {

		return  BindingBuilder.bind(queue()).to(delayExchange()).with("test_queue_1").noargs();
	}

	/**
	 *  实现死信
	 *  定义了普通交换机、邮件队列、死信交换机、死信队列
	 */
	//邮件队列名称
	final static String queue = "mail_queue";

	//邮件交换机名称
	final static String exchangeName = "mailExchange";

	// routingKey
	final static String routingKey  = "routing_key";

	//死信消息队列名称
	final static String deal_queue = "deal_queue";

	//死信交换机名称
	final static String deal_exchangeName = "deal_Exchange";

	//死信 routingKey
	final static String dead_RoutingKey  = "dead_routing_key";

	//死信队列 交换机标识符
	public static final String DEAD_LETTER_QUEUE_KEY = "x-dead-letter-exchange";

	//死信队列交换机绑定键标识符
	public static final String DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";

	@Autowired
	private CachingConnectionFactory connectionFactory;

	//定义邮件队列(邮件队列 绑定一个死信交换机,并指定routing_key)
	@Bean
	public Queue queueDemo3() {
		// 将普通队列绑定到死信队列交换机上
		Map<String, Object> args = new HashMap<>(2);
		args.put(DEAD_LETTER_QUEUE_KEY, deal_exchangeName);
		args.put(DEAD_LETTER_ROUTING_KEY, dead_RoutingKey);
		return new Queue(RabbitMQConfiguration.queue, true, false, false, args);
	}

	//声明一个direct类型的交换机
	@Bean
	DirectExchange exchangeDemo3() {
		return new DirectExchange(RabbitMQConfiguration.exchangeName);
	}

	//绑定邮件Queue队列到交换机,并且指定routingKey
	@Bean
	Binding bindingDirectExchangeDemo3(   ) {
		return BindingBuilder.bind(queueDemo3()).to(exchangeDemo3()).with(routingKey);
	}

	//创建配置死信邮件队列
	@Bean
	public Queue deadQueue() {
		Queue queue = new Queue(deal_queue, true);
		return queue;
	}

	//创建死信交换机
	@Bean
	public DirectExchange deadExchange() {
		return new DirectExchange(deal_exchangeName);
	}

	//死信队列与死信交换机绑定
	@Bean
	public Binding bindingDeadExchange() {
		return BindingBuilder.bind(deadQueue()).to(deadExchange()).with(dead_RoutingKey);
	}

	@Bean
	public RabbitTemplate rabbitTemplate(){
		//若使用confirm-callback ，必须要配置publisherConfirms 为true
		connectionFactory.setPublisherConfirms(true);
		//若使用return-callback，必须要配置publisherReturns为true
		connectionFactory.setPublisherReturns(true);
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		//使用return-callback时必须设置mandatory为true，或者在配置中设置mandatory-expression的值为true
		// rabbitTemplate.setMandatory(true);

		// 如果消息没有到exchange,则confirm回调,ack=false; 如果消息到达exchange,则confirm回调,ack=true
		rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
			@Override
			public void confirm(CorrelationData correlationData, boolean ack, String cause) {
				if(ack){
					log.info("消息发送成功:correlationData({}),ack({}),cause({})",correlationData,ack,cause);
				}else{
					log.info("消息发送失败:correlationData({}),ack({}),cause({})",correlationData,ack,cause);
				}
			}
		});

		//如果exchange到queue成功,则不回调return;如果exchange到queue失败,则回调return(需设置mandatory=true,否则不回回调,消息就丢了)
		rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
			@Override
			public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
				log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}",exchange,routingKey,replyCode,replyText,message);
			}
		});
		return rabbitTemplate;
	}

}
