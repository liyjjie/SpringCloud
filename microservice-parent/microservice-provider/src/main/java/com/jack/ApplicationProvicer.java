package com.jack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author ：liyongjie
 * @ClassName ：ApplicationProvicer
 * @date ： 2019-10-15 09:54
 * @modified By：
 */

@SpringBootApplication
@EnableEurekaClient  //本服务启动后会自动注册进eureka服务中
public class ApplicationProvicer {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationProvicer.class);
    }
}
