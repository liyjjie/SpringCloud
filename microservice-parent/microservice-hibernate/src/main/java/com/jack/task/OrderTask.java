package com.jack.task;

import com.jack.entity.Order;
import com.jack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.persistence.Column;

/**
 * @author ：liyongjie
 * @ClassName ：OrderTask
 * @date ： 2019-11-04 09:13
 * @modified By：
 */
//@Component
//@EnableScheduling
public class OrderTask {

    @Autowired
    private UserService userService;

    @Scheduled(fixedDelay = 3000)
    public void orderTask(){
      Order order=userService.getOrderFindBy(1);
        System.out.println(order);
    }
}
