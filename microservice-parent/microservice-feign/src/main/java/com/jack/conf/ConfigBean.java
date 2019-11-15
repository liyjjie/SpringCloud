package com.jack.conf;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author ：liyongjie
 * @ClassName ：ConfigBean
 * @date ： 2019-11-11 10:33
 * @modified By：
 */
@Configuration
public class ConfigBean {

    @Bean
    @LoadBalanced //Ribbon是实现客户端的负载均衡工具
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    public IRule myRule(){
        return new RoundRobinRule(); 	//轮询策略
    }
}
