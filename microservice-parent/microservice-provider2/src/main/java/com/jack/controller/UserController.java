package com.jack.controller;

import com.jack.entity.User;
import com.jack.service.UserService;
import com.jack.vo.AdressEndUserVo;
import com.jack.vo.OrderVo;
import com.jack.vo.PasswordVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author ：liyongjie
 * @ClassName ：UserController
 * @date ： 2019-10-15 16:07
 * @modified By：
 */
@RestController
@RequestMapping("/UserController")
public class UserController {

    @Value("${spring.application.name}")
    private String demo;

    @Resource
    private UserService userService;

    @ApiOperation(value = "添加数据")
    @PostMapping(value="/add")
    public Boolean addUser(@RequestBody User user){
       return userService.addUser(user);
    }

    @ApiOperation(value = "根据id获取数据")
    @GetMapping(value="/get/{id}")
    public User getUser(@PathVariable("id") int id){
        User user = userService.getUser(id);
        return user;
    }

    @ApiOperation(value = "返回所有数据")
    @GetMapping(value="/getUser/list")
    public List<User> getUsers(){
        List<User> users = userService.getUsers();
        return users;
    }

    @ApiOperation(value = "获取数据")
    @GetMapping(value = "/all")
    public List<AdressEndUserVo> all(){
      return  userService.getAll();
    }

    @ApiOperation(value = "订单数据")
    @GetMapping(value = "/orderAll")
    public List<OrderVo> orderAll(){
        return userService.getOrderAll();
    }

    @ApiOperation(value = "批量导入数据")
    @PostMapping(value = "/passwordInsert")
    public void passwordInsert(@RequestBody @Valid List<PasswordVo> list){
        userService.passwordInsert(list);
    }

    @ApiOperation(value = "数据处理")
    @PostMapping(value = "/demoInsert")
    public void demoInsert(@RequestBody OrderVo orderVo){
        userService.insertDemo(orderVo);
    }
}
