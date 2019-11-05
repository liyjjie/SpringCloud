package com.jack.service.Impl;

import com.jack.dao.HibernateSwagger;
import com.jack.entity.User;
import com.jack.service.SwaggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ：liyongjie
 * @ClassName ：SwaggerServiceImpl
 * @date ： 2019-11-05 14:39
 * @modified By：
 */
@Service
public class SwaggerServiceImpl implements SwaggerService {

    @Autowired
    private HibernateSwagger hibernateSwagger;

    @Transactional
    @Override
    public User getUserById(Integer id){
      User user=hibernateSwagger.getUserById(id);
      return user;
    }

}
