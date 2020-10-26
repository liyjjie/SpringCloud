package com.jack.service.Impl;

import com.jack.dao.PasswordDao;
import com.jack.dao.UserDao;
import com.jack.entity.User;
import com.jack.service.UserService;
import com.jack.vo.AdressEndUserVo;
import com.jack.vo.OrderVo;
import com.jack.vo.PasswordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ：liyongjie
 * @ClassName ：UserServiceImpl
 * @date ： 2019-10-15 16:09
 * @modified By：
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordDao passwordDao;

    @Override
    public Boolean addUser(User user) {
       return userDao.addUser(user);
    }

    @Override
    public User getUser(int id) {
        User user = userDao.getUser(id);
        System.out.println("microservice-provider2微服务在响应客户端请求……");
        System.out.println("user : " + user);
        return user;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = userDao.getUsers();
        return users;
    }

    @Override
    public List<AdressEndUserVo> getAll(){
       return userDao.getAll();
    }

    @Override
    public List<OrderVo> getOrderAll(){
        return userDao.getOrderAll();
    }

    @Override
    public void passwordInsert(List<PasswordVo> list){
        passwordDao.passwordInsert(list);
    }

    @Override
    @Transactional
    public void insertDemo(OrderVo orderVo){
        userDao.insertDemo(orderVo);
    }
}
