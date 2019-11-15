package com.jack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @author ：liyongjie
 * @ClassName ：ApplicationFeign
 * @date ： 2019-11-11 10:30
 * @modified By：
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages="com.jack.service")
@EnableCircuitBreaker
public class ApplicationFeign {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationFeign.class,args);
    }
}
