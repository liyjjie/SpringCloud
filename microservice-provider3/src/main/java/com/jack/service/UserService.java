package com.jack.service;

import com.jack.entity.User;

import java.util.List;

/**
 * @author ：liyongjie
 * @ClassName ：UserService
 * @date ： 2019-10-16 09:08
 * @modified By：
 */
public interface UserService {

    boolean addUser(User user);

    User getUser(int id);

    List<User> getUsers();
}
