package com.jack.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ：liyongjie
 * @ClassName ：Address
 * @date ： 2019-10-31 15:22
 * @modified By：
 */
@Entity
@Table(name = "t_address")
public class Address implements Serializable {

    private int id;

    private String address;

    private String addressName;

    private String phoneNumber;

    private User user;

    private Integer version;

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
    @Column(name="address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name="address_name")
    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    @Basic
    @Column(name="phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    @JsonBackReference
    public void setUser(User user) {
        this.user = user;
    }

    @Version
    @Column(name="version")
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", addressName='" + addressName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", user=" + user +
                ", version=" + version +
                '}';
    }
}
