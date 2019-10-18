package com.jack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @author ：liyongjie
 * @ClassName ：ApplicationConsumerFeign
 * @date ： 2019-10-16 09:30
 * @modified By：
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.jack.service")
public class ApplicationConsumerFeign {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationConsumerFeign.class,args);
    }
}
