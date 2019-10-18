package com.jack.controller;

import com.jack.entity.User;
import com.jack.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：liyongjie
 * @ClassName ：UserController
 * @date ： 2019-10-16 13:29
 * @modified By：
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/get/{id}")
    @HystrixCommand(fallbackMethod = "hystrixGetUser")
    public User getUser(@PathVariable("id") int id){
       User user=userService.getUser(id);
       if(user==null){
           throw new RuntimeException("不存在id="+id+"对应的用户信息");
       }
       return user;
    }

    public User hystrixGetUser(@PathVariable("id") int id){
        User user=new User(id,"不存在该用户",0);
        return user;
    }

}
