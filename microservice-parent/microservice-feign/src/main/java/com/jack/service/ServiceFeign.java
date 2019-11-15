package com.jack.service;

import com.jack.entity.User;
import com.jack.service.Impl.ServiceFeignImpl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author ：liyongjie
 * @ClassName ：ServiceFeign
 * @date ： 2019-11-11 10:38
 * @modified By：
 */
@FeignClient(value = "microservice-config-dev",fallbackFactory = ServiceFeignImpl.class)
public interface ServiceFeign {

    @GetMapping(value="/getUser/list")
    List<User> getAll();

    @GetMapping(value = "/get/{id}")
    User getUser(@PathVariable("id") int id);
}
