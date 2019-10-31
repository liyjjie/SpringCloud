package com.jack.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * @author ：liyongjie
 * @ClassName ：User
 * @date ： 2019-10-21 13:58
 * @modified By：
 */
@Table(name="t_user")
@Entity
public class User {
    private int id;

    private String name;

    private int age;

    private Set<Address> address;

    private Integer version = 0;

    public static final String COL_ID="id";
    public static final String COL_NAME="name";
    public static final String COL_AGE="age";

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
    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name="age")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @OneToMany(mappedBy = "user")
    @Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    public Set<Address> getAddress() {
        return address;
    }

    public void setAddress(Set<Address> address) {
        this.address = address;
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
                ", address=" + address +
                ", version=" + version +
                '}';
    }
}
