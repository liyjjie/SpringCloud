package com.jack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author ：liyongjie
 * @ClassName ：ApplicationProviderHystrix
 * @date ： 2019-10-16 13:27
 * @modified By：服务熔断
 */
@SpringBootApplication
@EnableEurekaClient //本服务启动后会自动注册进eureka服务中
@EnableCircuitBreaker
public class ApplicationProviderHystrix {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationProviderHystrix.class,args);
    }
}
