package com.jack.micservice;

import com.jack.entity.UserEntity;

/**
 * @author ：liyongjie
 * @ClassName ：MicService
 * @date ： 2019-11-11 14:47
 * @modified By：
 */
public interface MicService {

    UserEntity getUserById(int id);
}
