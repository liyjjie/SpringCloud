package com.jack.vo;

/**
 * @author ：liyongjie
 * @ClassName ：ResultVo
 * @date ： 2019-11-27 14:35
 * @modified By：
 */
public class ResultVo {

    private String name;

    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "ResultVo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
