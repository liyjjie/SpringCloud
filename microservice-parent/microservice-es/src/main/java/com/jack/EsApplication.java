package com.jack;

import com.jack.conf.EsConf;
import com.jack.es.EsClientFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * @author ：liyongjie
 * @ClassName ：EsApplication
 * @date ： 2019-11-25 17:01
 * @modified By：
 */
@SpringBootApplication
@EnableEurekaClient
@EnableConfigurationProperties({EsConf.class})
//@EnableScheduling
public class EsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EsApplication.class,args);
    }

    @Bean(name = "EsClientFactory",initMethod = "connect")
    public EsClientFactory esClientFactory(){
        return new EsClientFactory();
    }
}