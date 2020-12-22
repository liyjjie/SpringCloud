package com.jack.service.Impl;

/**
 * @author ：liyongjie
 * @ClassName ：GenRandomStrUtils
 * @date ： 2020-12-22 10:15
 * @modified By：生成不重复的随机串
 */
public class GenRandomStrUtils {
    //字符源
    private static final String GEN_SOURCE = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * 生成不重复随机字符串包括字母数字
     *
     * @param len 长度
     * @return
     */
    public static String generateRandomStr(int len) {
        String generateSource = GEN_SOURCE;
        StringBuilder rtnStr = new StringBuilder();
        for (int i = 0; i < len; i++) {
            //循环随机获得当次字符，并移走选出的字符
            String nowStr = String.valueOf(generateSource.charAt((int) Math.floor(Math.random() * generateSource.length())));
            rtnStr.append(nowStr);
            generateSource = generateSource.replaceAll(nowStr, "");
        }
        return rtnStr.toString();
    }
}
