package com.jack.micservice.Impl;

import com.jack.dao.ServiceDao;
import com.jack.entity.UserEntity;
import com.jack.micservice.MicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional
    public UserEntity getUserById(int id) {
        return serviceDao.getUserById(id);
    }
}
