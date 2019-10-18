package com.jack.service.Impl;

import com.jack.dao.OrderDao;
import com.jack.entity.Order;
import com.jack.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：liyongjie
 * @ClassName ：OrderServiceImpl
 * @date ： 2019-10-17 10:37
 * @modified By：
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public Boolean addOrder(Order order) {
      Boolean flag=orderDao.addOrder(order);
        return flag;
    }

    @Override
    public Order getOrder(int id) {
      Order order=orderDao.getOrder(id);
        System.out.println("microservice-demo被调用");
        return order;
    }

    @Override
    public List<Order> getAllOrder() {
       List<Order> list=orderDao.getAllOrder();
        return list;
    }
}
