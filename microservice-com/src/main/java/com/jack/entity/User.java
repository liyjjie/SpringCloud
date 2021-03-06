package com.jack.entity;

/**
 * @author ：liyongjie
 * @ClassName ：User
 * @date ： 2019-10-15 09:40
 * @modified By：
 */
public class User {

    private int id;

    private String name;

    private int age;

    private int version;

    public User() {
    }


    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", version=" + version +
                '}';
    }
}
