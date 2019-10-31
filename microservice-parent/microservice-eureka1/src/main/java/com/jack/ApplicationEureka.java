package com.jack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author ：liyongjie
 * @ClassName ：ApplicationEureka
 * @date ： 2019-10-15 11:32
 * @modified By：
 */
@SpringBootApplication
@EnableEurekaServer
public class ApplicationEureka {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationEureka.class,args);
    }
}
