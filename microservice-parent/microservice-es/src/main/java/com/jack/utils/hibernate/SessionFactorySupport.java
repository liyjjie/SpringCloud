package com.jack.utils.hibernate;

import org.hibernate.SessionFactory;

/**
 * @author ：liyongjie
 * @ClassName ：SessionFactorySupport
 * @date ： 2019-11-28 09:43
 * @modified By：
 */
public class SessionFactorySupport {

    public SessionFactorySupport() {
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        System.out.println("SessionFactorySupport setSessionFactory...");
        com.jack.utils.hibernate.SessionFactory.getInstance().sessionFactory = sessionFactory;
    }

}
