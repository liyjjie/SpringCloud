package com.jack.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：liyongjie
 * @ClassName ：ConfigBean
 * @date ： 2019-11-11 14:35
 * @modified By：
 */
@Configuration
public class ConfigBean {

    @Bean
    public IRule myRule(){
        return new RoundRobinRule(); 	//轮询策略
    }
}
