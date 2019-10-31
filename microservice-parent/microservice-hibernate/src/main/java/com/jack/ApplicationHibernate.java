package com.jack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author ：liyongjie
 * @ClassName ：ApplicationHibernate
 * @date ： 2019-10-21 13:49
 * @modified By：
 */
@SpringBootApplication
@EnableEurekaClient
public class ApplicationHibernate {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationHibernate.class,args);
    }
}
