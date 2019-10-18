package com.jack.dao;

import com.jack.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ：liyongjie
 * @ClassName ：OrderDao
 * @date ： 2019-10-17 10:29
 * @modified By：
 */
@Mapper
public interface OrderDao {

    Order getOrder(int id);

    List<Order> getAllOrder();

    Boolean addOrder(Order order);
}
