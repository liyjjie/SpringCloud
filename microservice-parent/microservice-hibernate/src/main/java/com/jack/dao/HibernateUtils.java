package com.jack.dao;

import com.jack.entity.Address;
import com.jack.entity.Order;
import com.jack.entity.User;
import com.jack.vo.AddressVo;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    /**在一对多查询时会出现两条相同的数据尽量避免
     * 当一的一方唯一时用uniqueResult
     * 当不确定时才用list
     */
    public List<User> getOrderByPhoneNumber(AddressVo addressVo) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(User.class);
        Criteria criteria1 = criteria.createCriteria(User.COL_ADDRESS, JoinType.LEFT_OUTER_JOIN);
        criteria.add(Restrictions.eq(User.COL_ID, 2));
        criteria1.add(Restrictions.eq(Address.COL_PHONE_NUMBER, addressVo.getPhoneNumber()));
        List<User> user = criteria.list();
        return user;
    }

    public void saveUser(User user){
        Session session=sessionFactory.getCurrentSession();
        session.save(user);
    }
}
