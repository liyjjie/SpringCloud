package com.jack.service;

import com.jack.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author ：liyongjie
 * @ClassName ：ConsumerService
 * @date ： 2019-10-16 14:09
 * @modified By：
 */
/*以后调用microservicecloud-provider微服务中的方法，只需要调用下面对应的接口既可以了*/
@FeignClient(value="microservice-provider", fallbackFactory= ConsumerServiceFallbackFactory.class)
public interface ConsumerService {

    /*调用接口中的get方法，即可以向microservicecloud-provider微服务发送/get/{id}请求*/
    @GetMapping(value="/get/{id}")
    public User get(@PathVariable("id") int id);

    @PostMapping(value="/add")
    public boolean add(User user);

    @GetMapping(value="/getUser/list")
    public List<User> getAll();
}
