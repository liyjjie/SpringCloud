package com.jack.domain;

import javax.persistence.*;

/**
 * @author ：liyongjie
 * @ClassName ：User
 * @date ： 2019-11-28 11:04
 * @modified By：
 */
@Entity
@Table(name = "t_user")
public class User {

    private Long id;

    private String name;

    private Integer age;

    private Integer version = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

