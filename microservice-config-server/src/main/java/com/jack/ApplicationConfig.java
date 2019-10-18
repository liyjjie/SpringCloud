package com.jack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author ：liyongjie
 * @ClassName ：ApplicationConfig
 * @date ： 2019-10-17 14:08
 * @modified By：
 */
@SpringBootApplication
@EnableConfigServer
public class ApplicationConfig {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationConfig.class,args);
    }
}
