package com.jack.service;

import com.jack.entity.Address;
import com.jack.entity.Order;
import com.jack.entity.User;
import com.jack.vo.AddressVo;

/**
 * @author ：liyongjie
 * @ClassName ：UserService
 * @date ： 2019-10-21 13:59
 * @modified By：
 */
public interface UserService {

    User getFindById(int id);

    Address getFindByAddress(int id);

    Order getOrderFindBy(int id);

    Integer updateAddressFindBy(AddressVo addressVo);
}
