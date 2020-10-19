package com.jack.service;

import com.jack.entity.User;
import com.jack.vo.AdressEndUserVo;
import com.jack.vo.OrderVo;
import com.jack.vo.PasswordVo;

import java.util.List;

/**
 * @author ：liyongjie
 * @ClassName ：UserService
 * @date ： 2019-10-15 16:08
 * @modified By：
 */
public interface UserService {

    Boolean addUser(User user);

    User getUser(int id);

    List<User> getUsers();

    List<AdressEndUserVo> getAll();

    List<OrderVo> getOrderAll();

    void passwordInsert(List<PasswordVo> list);
}
