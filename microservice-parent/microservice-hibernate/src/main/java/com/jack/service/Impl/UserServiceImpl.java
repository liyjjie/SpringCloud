package com.jack.service.Impl;

import com.jack.dao.HibernateUtils;
import com.jack.entity.Address;
import com.jack.entity.User;
import com.jack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ：liyongjie
 * @ClassName ：UserServiceImpl
 * @date ： 2019-10-21 14:03
 * @modified By：
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private HibernateUtils hibernateUtils;

    @Transactional
    @Override
    public User getFindById(int id){
      User userHibernate=hibernateUtils.getFindBy(id);
      return userHibernate;
    }

    @Transactional
    @Override
    public Address getFindByAddress(int id){
       Address address=hibernateUtils.getFindByAddress(id);
       return  address;
    }

}
