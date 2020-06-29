package com.woniuxy.main.dead;

import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 生产者
 * @author  Mr Zay
 * @time 2020.6.24
 */
@Component
public class MailSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * @method 生产者发送消息,direct模式下需要传递一个routingKey
     * @author Mr Z
     * @time 2020.6.24
     */

    public  void send() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", "123232332@qq.com");
        jsonObject.put("message", "hello");
        jsonObject.put("timestamp", 0);
        String jsonStr = jsonObject.toJSONString();
        System.out.println("生产者发送的消息是:" + jsonStr);
        // 设置消息唯一id 保证每次从事消息的id唯一性
        Message message = MessageBuilder.withBody(jsonStr.getBytes()).setContentType(MessageProperties.CONTENT_TYPE_JSON).setContentEncoding("utf-8")
                .setMessageId(UUID.randomUUID() + "").build(); //消息id设置在请求头里面 用UUID做全局ID

        // 发送消息(交换机，routingkey，msg)
        amqpTemplate.convertAndSend("mailExchange","routing_key",message);
    }

}
