package com.jack.vo;

import com.jack.domain.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author ：liyongjie
 * @ClassName ：UserVo
 * @date ： 2019-12-09 16:19
 * @modified By：
 */
@ApiModel(value = "UserVo",description = "用户信息")
public class UserVo {

    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "年龄")
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
