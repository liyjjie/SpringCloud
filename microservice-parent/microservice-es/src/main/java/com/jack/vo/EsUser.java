package com.jack.vo;

import io.searchbox.annotations.JestId;

import java.io.Serializable;

/**
 * @author ：liyongjie
 * @ClassName ：EsUser
 * @date ： 2019-12-06 10:36
 * @modified By：
 */
public class EsUser implements Serializable {

    private static final long serialVersionUID = 1790910592565030164L;

    @JestId
    private String id;

    private String name;

    private String address;

    private Integer age;

    private String iphone;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getIphone() {
        return iphone;
    }

    public void setIphone(String iphone) {
        this.iphone = iphone;
    }

    @Override
    public String toString() {
        return "EsUser{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", iphone='" + iphone + '\'' +
                '}';
    }
}
