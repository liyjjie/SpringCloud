package com.jack.service.Impl;

import com.jack.dao.UserDao;
import com.jack.entity.User;
import com.jack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：liyongjie
 * @ClassName ：UserServiceImpl
 * @date ： 2019-10-16 13:36
 * @modified By：
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDao userDao;

    @Override
    public boolean addUser(User user) {
        boolean flag;
        flag = userDao.addUser(user);
        return flag;
    }

    @Override
    public User getUser(int id) {
        User user = userDao.getUser(id);
        System.out.println("microservice-provider微服务在响应客户端请求……");
        System.out.println("user : " + user);
        return user;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = userDao.getUsers();
        return users;
    }


}
