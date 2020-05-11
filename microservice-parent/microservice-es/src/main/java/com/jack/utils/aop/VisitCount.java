package com.jack.utils.aop;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ：liyongjie
 * @ClassName ：VisitCount
 * @date ： 2020-01-02 15:27
 * @modified By：
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface VisitCount {
}
