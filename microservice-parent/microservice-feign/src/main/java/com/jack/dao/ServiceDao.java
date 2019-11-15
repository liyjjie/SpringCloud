package com.jack.dao;

import com.jack.entity.UserEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author ：liyongjie
 * @ClassName ：ServiceDao
 * @date ： 2019-11-11 14:47
 * @modified By：
 */
@Repository
public class ServiceDao {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    public UserEntity getUserById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(UserEntity.class);
        criteria.add(Restrictions.eq(UserEntity.COL_ID, id));
        UserEntity userEntity = (UserEntity) criteria.uniqueResult();
        return userEntity;
    }
}
