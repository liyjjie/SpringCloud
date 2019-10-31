package com.jack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author ：liyongjie
 * @ClassName ：ApplicationZull
 * @date ： 2019-10-16 15:16
 * @modified By：
 */
@SpringBootApplication
@EnableZuulProxy
public class ApplicationZull {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationZull.class,args);
    }
}
