package com.jack.controller;

import com.jack.entity.User;
import com.jack.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ：liyongjie
 * @ClassName ：UserController
 * @date ： 2019-10-15 10:05
 * @modified By：
 */
//@Api("测试")
@RestController
//@RequestMapping("/test")
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

//    @ApiOperation("返回传入参数")
//    @ApiImplicitParams(@ApiImplicitParam(name = "id",paramType = "path",value = "id",required = true,dataType = "Integer"))
//    @GetMapping("/{id}")
//    public Integer getAll(@PathVariable("id") int id){
//        return id;
//    }
}
