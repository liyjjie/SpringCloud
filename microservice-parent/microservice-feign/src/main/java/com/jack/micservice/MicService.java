package com.jack.micservice;

import com.jack.entity.User;
import com.jack.entity.UserEntity;

import java.util.List;

/**
 * @author ：liyongjie
 * @ClassName ：MicService
 * @date ： 2019-11-11 14:47
 * @modified By：
 */
public interface MicService {

    UserEntity getUserById(int id);

    List<User> getUserAll();

    User getById(int id);
}
