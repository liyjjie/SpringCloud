package com.jack.dao;

import com.jack.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * @author ：liyongjie
 * @ClassName ：HibernateSwagger
 * @date ： 2019-11-05 14:35
 * @modified By：
 */
@Repository
public class HibernateSwagger {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    public User getUserById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq(User.COL_ID, id));
        User user = (User) criteria.uniqueResult();
        return user;
    }
}
