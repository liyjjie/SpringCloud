package com.jack.service.Impl;

import com.jack.entity.User;
import com.jack.service.ServiceFeign;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：liyongjie
 * @ClassName ：ServiceFeignImpl
 * @date ： 2019-11-11 14:22
 * @modified By：
 */
@Component
public class ServiceFeignImpl implements FallbackFactory<ServiceFeign> {

    @Override
    public ServiceFeign create(Throwable arg0) {


        return new ServiceFeign() {

            @Override
            public List<User> getAll(){
                List<User> list=new ArrayList<>();
                User user=new User(0, "数据库无数据", 0);
                list.add(user);
                return list;
            }

            @Override
            public User getUser(int id) {
                User user = new User(id, "该用户不存在", 0);
                return user;
            }
        };
    }
}
