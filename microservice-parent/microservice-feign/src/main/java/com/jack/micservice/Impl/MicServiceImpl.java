package com.jack.micservice.Impl;

import com.jack.dao.ServiceDao;
import com.jack.entity.User;
import com.jack.entity.UserEntity;
import com.jack.micservice.MicService;
import com.jack.service.ServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ：liyongjie
 * @ClassName ：MicServiceImpl
 * @date ： 2019-11-11 15:02
 * @modified By：
 */
@Service
public class MicServiceImpl implements MicService {

    @Autowired
    private ServiceDao serviceDao;

    @Autowired
    private ServiceFeign serviceFeign;

    @Override
    @Transactional
    public UserEntity getUserById(int id) {
        return serviceDao.getUserById(id);
    }

    @Override
    @Transactional
    public List<User> getUserAll(){
        List<User> list=serviceFeign.getAll();
        return list;
    }

    @Override
    @Transactional
    public User getById(int id){
       return serviceFeign.getUser(id);
    }
}
