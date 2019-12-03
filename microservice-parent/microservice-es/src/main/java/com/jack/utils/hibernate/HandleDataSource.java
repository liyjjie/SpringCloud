package com.jack.utils.hibernate;

/**
 * @author ：liyongjie
 * @ClassName ：HandleDataSource
 * @date ： 2019-11-28 09:55
 * @modified By：
 */
public class HandleDataSource {

    public static final ThreadLocal<Boolean> holder = new ThreadLocal();

    public HandleDataSource() {
    }

    public static void putDataSource(boolean readOnly) {
        holder.set(readOnly);
    }

    public static Boolean getDataSource() {
        return (Boolean)holder.get();
    }

    public static void remove() {
        holder.remove();
    }
}
