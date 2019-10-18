package com.jack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author ：liyongjie
 * @ClassName ：ApplicationConsumerHystrixDashbord
 * @date ： 2019-10-16 14:33
 * @modified By：实时监控
 */
@SpringBootApplication
@EnableHystrixDashboard
public class ApplicationConsumerHystrixDashbord {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationConsumerHystrixDashbord.class,args);
    }
}
