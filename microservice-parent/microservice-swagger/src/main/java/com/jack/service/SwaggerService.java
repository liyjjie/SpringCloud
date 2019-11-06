package com.jack.service;

import com.jack.entity.User;

/**
 * @author ：liyongjie
 * @ClassName ：SwaggerService
 * @date ： 2019-11-05 14:39
 * @modified By：
 */
public interface SwaggerService {

    User getUserById(Integer id);

    void getRedisPool() throws Exception;
}
