package com.woniuxy.main.dead;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;

/**
 * 死信邮件消费者，消费从死信队列传来的消息
 */

@Component
public class DeadReceive {

    @RabbitListener(queues = "deal_queue")
    public void deadReceive(Message message, @Headers Map<String, Object> headers, Channel channel) throws IOException {

        // 获取死信消费者的消息id
        String messageId = message.getMessageProperties().getMessageId();
        String msg = new String(message.getBody(),"UTF-8");
        System.out.println("死信邮件消费者获取生产者消息msg:"+msg+",消息id"+messageId);

        // 手动ack
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        // 手动签收
        channel.basicAck(deliveryTag, false);
        System.out.println("执行结束....");

    }

}
