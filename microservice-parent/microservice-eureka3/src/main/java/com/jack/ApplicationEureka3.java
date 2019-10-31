package com.jack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author ：liyongjie
 * @ClassName ：ApplicationEureka3
 * @date ： 2019-10-15 13:41
 * @modified By：
 */
@SpringBootApplication
@EnableEurekaServer
public class ApplicationEureka3 {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationEureka3.class,args);
    }
}
