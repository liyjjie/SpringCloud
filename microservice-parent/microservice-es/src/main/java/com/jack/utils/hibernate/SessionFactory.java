package com.jack.utils.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author ：liyongjie
 * @ClassName ：SessionFactory
 * @date ： 2019-11-28 09:42
 * @modified By：
 */
public class SessionFactory {

    private static volatile SessionFactory instance = null;
    org.hibernate.SessionFactory sessionFactory;

    private SessionFactory() {
        System.out.println("SessionFactory is init...");
    }

    public static SessionFactory getInstance() {
        if (instance == null) {
            Class var0 = SessionFactory.class;
            synchronized(SessionFactory.class) {
                if (instance == null) {
                    instance = new SessionFactory();
                }
            }
        }

        return instance;
    }

    public Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public long nextSeq(String seqName) {
        Session session = this.getSession();
        Query q = session.createSQLQuery("select " + seqName + ".nextval as seq from dual");
        String a = String.valueOf(q.uniqueResult());
        return Long.valueOf(a);
    }
}
