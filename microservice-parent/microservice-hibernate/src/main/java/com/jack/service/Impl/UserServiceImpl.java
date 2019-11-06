package com.jack.service.Impl;

import com.jack.dao.HibernateUtils;
import com.jack.entity.Address;
import com.jack.entity.Order;
import com.jack.entity.User;
import com.jack.service.UserService;
import com.jack.vo.AddressVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ：liyongjie
 * @ClassName ：UserServiceImpl
 * @date ： 2019-10-21 14:03
 * @modified By：
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private HibernateUtils hibernateUtils;

    @Transactional
    @Override
    public User getFindById(int id) {
        User userHibernate = hibernateUtils.getFindBy(id);
        return userHibernate;
    }

    @Transactional
    @Override
    public Address getFindByAddress(int id) {
        Address address = hibernateUtils.getFindByAddress(id);
        return address;
    }

    @Transactional
    @Override
    public Order getOrderFindBy(int id) {
        Order order = hibernateUtils.getOrderById(id);
        return order;
    }

    @Override
    @Transactional
    public Integer updateAddressFindBy(AddressVo addressVo) {
        List<User> list = hibernateUtils.getOrderByPhoneNumber(addressVo);
        for (User temp: list ) {
            Set<Address> startSet=new HashSet<>();
            for (Address set: temp.getAddress()) {
                set.setAddressName(addressVo.getAddressName());
                set.setAddress(addressVo.getAddress());
                set.setPhoneNumber(addressVo.getPhoneNumber());
                startSet.add(set);
            }
            temp.setAddress(startSet);
            hibernateUtils.saveUser(temp);
        }
        return Integer.valueOf(1);
    }
}
