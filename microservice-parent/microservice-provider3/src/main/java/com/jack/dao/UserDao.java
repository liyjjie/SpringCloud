package com.jack.dao;

import com.jack.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ：liyongjie
 * @ClassName ：UserDao
 * @date ： 2019-10-16 09:08
 * @modified By：
 */
@Mapper
public interface UserDao {

    Boolean addUser(User user);

    User getUser(int id);

    List<User> getUsers();
}
