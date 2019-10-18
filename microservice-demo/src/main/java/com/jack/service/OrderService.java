package com.jack.service;

import com.jack.entity.Order;
import org.springframework.cloud.netflix.feign.FeignClient;

import java.util.List;

/**
 * @author ：liyongjie
 * @ClassName ：OrderService
 * @date ： 2019-10-17 10:35
 * @modified By：
 */
public interface OrderService {

    Order getOrder(int id);

    List<Order> getAllOrder();

    Boolean addOrder(Order order);
}
