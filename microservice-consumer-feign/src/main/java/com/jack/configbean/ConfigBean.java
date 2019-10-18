package com.jack.configbean;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author ：liyongjie
 * @ClassName ：ConfigBean
 * @date ： 2019-10-16 13:59
 * @modified By：
 */
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
