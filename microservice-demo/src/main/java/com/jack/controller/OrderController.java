package com.jack.controller;

import com.jack.entity.Order;
import com.jack.service.OrderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：liyongjie
 * @ClassName ：OrderController
 * @date ： 2019-10-17 10:40
 * @modified By：
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/demo/{id}")
    @HystrixCommand(fallbackMethod = "hystrixGetOrder")
    public Order getFindById(@PathVariable("id") int id){
      Order order=orderService.getOrder(id);
      if(order==null){
          throw new RuntimeException("不存在此id的值");
      }
      return order;
    }


    public Order hystrixGetOrder(@PathVariable("id") int id){
        Order order=new Order(id,"无此id对应的信息","1",1);
        return order;
    }

}
