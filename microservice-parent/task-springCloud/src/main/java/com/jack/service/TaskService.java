package com.jack.service;

import com.jack.dao.UserDao;
import com.jack.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author ：liyongjie
 * @ClassName ：TaskService
 * @date ： 2020-09-25 15:49
 * @modified By：
 */
@Service
public class TaskService {

    @Autowired
    private UserDao userDao;

    public User getAll(){
       return userDao.getUser(1);
    }
}
