package com.woniuxy.main.delay;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ReceiveDelayMessage {
    // 监听的队列
    @RabbitListener(queues = "test_queue_1")
    public void receive(String msg) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    System.out.println("消费者接收消息时间："+simpleDateFormat.format(new Date()));
    System.out.println("消息是："+msg);
    }
}
