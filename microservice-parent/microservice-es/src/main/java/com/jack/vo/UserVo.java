package com.jack.vo;

import com.jack.domain.User;

/**
 * @author ：liyongjie
 * @ClassName ：UserVo
 * @date ： 2019-12-09 16:19
 * @modified By：
 */
public class UserVo {


    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    public User toVo(){
        User user=new User();
        user.setAge(this.getAge());
        user.setName(this.getName());
        return user;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
