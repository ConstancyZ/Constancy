package com.yuanchuang.demo.controller;

import com.github.pagehelper.PageInfo;
import com.yuanchuang.demo.dao.UserPojoMapper;
import com.yuanchuang.demo.entity.User;
import com.yuanchuang.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lzh
 * create 2019-09-18-22:36
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/queryAll")
    public PageInfo<User> queryAll(int currentPage, int pageSize){

        return  userService.queryAll(currentPage,pageSize);
    }

    @RequestMapping("/findUserById")
    public Map<String, Object> findUserById(@RequestParam int id){
        User user = userService.findUserById(id);
        Map<String, Object> result = new HashMap<>();
        result.put("id", user.getId());
        result.put("uname", user.getUsername());
        result.put("pass", user.getPassword());
        result.put("salary", user.getSalary());
        return result;
    }

    @RequestMapping("/updateUser")
    public String updateUser(){
        User user = new User();
        user.setId(5);
        user.setUsername("cat");
        user.setPassword("miaomiao");
        user.setSalary(4000.00);

        int result = userService.updateUser(user);

        if(result != 0){
            return "update user success";
        }

        return "fail";
    }

    @RequestMapping("/deleteUserById")
    public String deleteUserById(@RequestParam int id){
        int result = userService.deleteUserById(id);
        if(result != 0){
            return "delete success";
        }
        return "delete fail";
    }
}
