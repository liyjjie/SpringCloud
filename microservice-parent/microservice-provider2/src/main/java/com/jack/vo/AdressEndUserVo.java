package com.jack.vo;

import java.util.List;

/**
 * @author ：liyongjie
 * @ClassName ：AdressEndUserVo
 * @date ： 2020-10-13 13:41
 * @modified By：
 */
public class AdressEndUserVo {

    private Long id;

    private String address;

    private String addressName;

    private String phoneNumber;

    private UserVo userVos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserVo getUserVos() {
        return userVos;
    }

    public void setUserVos(UserVo userVos) {
        this.userVos = userVos;
    }
}
