package com.jack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author ：liyongjie
 * @ClassName ：ApplicationSwagger
 * @date ： 2019-11-05 14:11
 * @modified By：
 */
@SpringBootApplication
@EnableEurekaClient
public class ApplicationSwagger {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationSwagger.class,args);
    }
}
