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
 * 邮件消费者，消费从邮件队列传来的消息
 * @author Zay
 * @time 2020.06.24
 */
@Component
public class MailReceive {

    @RabbitListener(queues= "mail_queue")
    public void receive(Message message, @Headers Map<String, Object> headers, Channel channel) throws IOException {

        // 获取消息的id
        String messageId = message.getMessageProperties().getMessageId();
        String msg = new String(message.getBody(),"UTF-8");
        System.out.println("邮件消费者获取生产者消息msg:"+msg+",消息id"+messageId);

        JSONObject jsonObject = JSONObject.parseObject(msg);
        Integer timestamp = jsonObject.getInteger("timestamp");

        try {
            int result = 1/timestamp;
            System.out.println("result"+result);
            // 手动ack
            Long deliverTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
            // 手动签收
             channel.basicAck(deliverTag, false);
        } catch (Exception e) {
            //拒绝消费消息（丢失消息） 给死信队列,第三个参数 false 表示不会重回队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);

        }

    }
}
