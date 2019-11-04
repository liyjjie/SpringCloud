package com.jack.controller;

import com.jack.entity.Address;
import com.jack.entity.User;
import com.jack.service.UserService;
import com.jack.vo.UserReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：liyongjie
 * @ClassName ：UserHibernateUtils
 * @date ： 2019-10-21 14:00
 * @modified By：
 */
@RestController
public class UserHibernateUtils {

    @Autowired
    private UserService userService;

    @GetMapping("getFindById/{id}")
    public UserReturn getFindById(@PathVariable("id") int id) {
        User userHibernate = userService.getFindById(id);
        UserReturn userReturn=userHibernate.toVo();
        return userReturn;
    }

    @GetMapping("/getAddress/{id}")
    public Address getAddressById(@PathVariable("id") int id){
       Address address=userService.getFindByAddress(id);
       return address;
    }
}
