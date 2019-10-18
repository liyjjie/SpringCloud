package com.jack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author ：liyongjie
 * @ClassName ：ApplicationProvider2
 * @date ： 2019-10-15 16:05
 * @modified By：
 */
@SpringBootApplication
@EnableEurekaClient
public class ApplicationProvider2 {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationProvider2.class,args);
    }
}
