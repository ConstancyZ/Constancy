package com.woniuxy.main.handler;

import com.woniuxy.main.delay.SendDelayMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("delay")
public class DelayHandler {

    @Autowired
    private SendDelayMessage sendDelayMessage;

    @RequestMapping("test")
    public String send(){
        sendDelayMessage.sendMsg("test_queue_1","CSD你个憨憨");
        return "success";
    }
}
