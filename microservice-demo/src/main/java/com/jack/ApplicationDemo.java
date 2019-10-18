package com.jack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author ：liyongjie
 * @ClassName ：ApplicationDemo
 * @date ： 2019-10-17 09:29
 * @modified By：
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class ApplicationDemo {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationDemo.class,args);
    }
}
