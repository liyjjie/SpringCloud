package com.jack.controller;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ：liyongjie
 * @ClassName ：TryFactory
 * @date ： 2020-08-31 17:36
 * @modified By：测试
 */
public class TryFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(TryFactory.class.getName());

    /**
     * 重试
     *
     * @param interval 重试间隔
     * @param times    重试次数
     * @param method   重试执行方法
     * @param <R>      重试执行方法返回类型
     */
    private static <R> R tryTo(Long interval, Integer times, TryMethod<R> method) {
        R result = null;
        Integer tryTimes = 0;
        try {
            while (times > tryTimes && result == null) {
                tryTimes++;
                result = method.getResult();
                if (result == null && tryTimes < times) {
                    Thread.sleep(interval);
                }
            }
            return result;

        } catch (Throwable e) {
            LOGGER.error("", e);
        }
        return null;
    }

    private interface TryMethod<R> {
        R getResult();
    }

    @Test
    public void test1() {
        String s = "zhengbin";
        for (int i = 1; i < 4; i++) {
            final int fI = i;
            String res = TryFactory.tryTo(300L, 3, ()->getStr(s,fI));
            System.out.println("try suc,res:" + res);
        }
    }


    private String getStr(String str, Integer v) {
        if (v != 3) {
            System.out.println("try param:" + v);
            return null;
        }
        System.out.println("try:suc");
        return "result:" + str;
    }

}
