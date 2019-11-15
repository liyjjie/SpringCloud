package com.jack.entity;

import javax.persistence.*;

/**
 * @author ：liyongjie
 * @ClassName ：UserEntity
 * @date ： 2019-11-11 14:52
 * @modified By：
 */
@Entity
@Table(name = "t_user")
public class UserEntity {

    private int id;

    private String name;

    private Integer age;

    private Integer version=0;

    public static final String COL_ID="id";

    public UserEntity() {
    }

    public UserEntity(int id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Version
    @Column(name = "version")
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
