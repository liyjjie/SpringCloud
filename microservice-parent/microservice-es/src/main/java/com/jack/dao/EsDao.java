package com.jack.dao;

import com.jack.domain.User;
import com.jack.utils.HibernateUtils;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * @author ：liyongjie
 * @ClassName ：EsDao
 * @date ： 2019-11-26 09:06
 * @modified By：
 */
@Repository
public class EsDao {

    public User userByid(Long id) {
        Session session = HibernateUtils.getSession();
        User user = session.get(User.class, id);
        return user;
    }
}
