package com.woniuxy.main.handler;

import com.woniuxy.main.dead.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("dead")
@RestController
public class DeadHandler {

    @Autowired
    private MailSender mailSender;
    @RequestMapping("test")
    public String send() {
        mailSender.send();
        return "success";
    }
}
