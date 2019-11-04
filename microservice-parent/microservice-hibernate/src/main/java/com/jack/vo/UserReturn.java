package com.jack.vo;

import com.jack.entity.Address;

import java.util.Set;

/**
 * @author ：liyongjie
 * @ClassName ：UserReturn
 * @date ： 2019-11-04 10:00
 * @modified By：
 */
public class UserReturn {

    private int id;

    private String name;

    private int age;

    private Set<Address> address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<Address> getAddress() {
        return address;
    }

    public void setAddress(Set<Address> address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserReturn{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address=" + address +
                '}';
    }
}
