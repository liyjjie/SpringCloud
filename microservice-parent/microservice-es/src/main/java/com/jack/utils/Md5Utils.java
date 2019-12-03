package com.jack.utils;

import java.security.MessageDigest;

/**
 * @author ：liyongjie
 * @ClassName ：Md5Utils
 * @date ： 2019-11-28 10:29
 * @modified By：加密
 */
public class Md5Utils {

    public Md5Utils() {
    }

    public static String md5(String source) {
        StringBuffer sb = new StringBuffer(32);

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(source.getBytes("utf-8"));

            for(int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString(array[i] & 255 | 256).toUpperCase().substring(1, 3));
            }
        } catch (Exception var5) {
            return "";
        }

        return sb.toString();
    }
}
