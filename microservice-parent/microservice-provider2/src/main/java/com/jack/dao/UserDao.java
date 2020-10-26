package com.jack.dao;

import com.jack.entity.User;
import com.jack.vo.AdressEndUserVo;
import com.jack.vo.OrderVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ：liyongjie
 * @ClassName ：UserDao
 * @date ： 2019-10-15 16:08
 * @modified By：
 */
@Mapper
public interface UserDao {

    Boolean addUser(User user);

    User getUser(int id);

    List<User> getUsers();

    List<AdressEndUserVo> getAll();

    List<OrderVo> getOrderAll();

    void insertDemo(OrderVo orderVo);
}
