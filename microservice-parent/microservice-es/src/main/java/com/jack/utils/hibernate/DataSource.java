package com.jack.utils.hibernate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ：liyongjie
 * @ClassName ：DataSource
 * @date ： 2019-11-28 09:52
 * @modified By：
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface DataSource {
    boolean readOnly() default false;
}
