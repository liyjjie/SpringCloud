package com.jack.utils;

import com.jack.utils.hibernate.SessionFactory;
import org.hibernate.Session;

/**
 * @author ：liyongjie
 * @ClassName ：HibernateUtils
 * @date ： 2019-11-28 09:41
 * @modified By：
 */
public class HibernateUtils {

    public HibernateUtils() {
    }

    public static Session getSession() {
        return SessionFactory.getInstance().getSession();
    }

    public static long nextSeq(String seqName) {
        return SessionFactory.getInstance().nextSeq(seqName);
    }
}
