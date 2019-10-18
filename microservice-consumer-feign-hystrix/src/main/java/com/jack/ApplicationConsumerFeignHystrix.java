package com.jack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @author ：liyongjie
 * @ClassName ：ApplicationConsumerFeignHystrix
 * @date ： 2019-10-16 13:58
 * @modified By：服务降级
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages="com.jack.service")
@EnableCircuitBreaker
public class ApplicationConsumerFeignHystrix {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationConsumerFeignHystrix.class,args);
    }
}
