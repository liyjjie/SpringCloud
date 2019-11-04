package com.jack.dao;

import com.jack.entity.Address;
import com.jack.entity.Order;
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
 * @ClassName ：HibernateUtils
 * @date ： 2019-10-21 14:00
 * @modified By：
 */
@Repository
public class HibernateUtils {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;


    public User getFindBy(int id) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq(User.COL_ID, id));
        User userHibernate = (User) criteria.uniqueResult();
        return userHibernate;
    }

    public Address getFindByAddress(int id) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Address.class);
        criteria.add(Restrictions.eq("id", id));
        Address address = (Address) criteria.uniqueResult();
        return address;
    }

    public Order getOrderById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Order.class);
        criteria.add(Restrictions.eq(Order.COL_ID, id));
        Order order = (Order) criteria.uniqueResult();
        return order;
    }
}
