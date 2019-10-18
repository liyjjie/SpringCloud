package com.jack.controller;

import com.jack.entity.User;
import com.jack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ：liyongjie
 * @ClassName ：UserController
 * @date ： 2019-10-15 16:07
 * @modified By：
 */
@RestController
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping(value="/add")
    public boolean addUser(@RequestBody User user){
        boolean flag = service.addUser(user);
        return flag;
    }

    @GetMapping(value="/get/{id}")
    public User getUser(@PathVariable("id") int id){
        User user = service.getUser(id);
        return user;
    }

    @GetMapping(value="/getUser/list")
    public List<User> getUsers(){
        List<User> users = service.getUsers();
        return users;
    }
}
