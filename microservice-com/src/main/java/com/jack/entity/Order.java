package com.jack.entity;

/**
 * @author ：liyongjie
 * @ClassName ：Order
 * @date ： 2019-10-17 10:25
 * @modified By：
 */
public class Order {
    private int id;

    private String orderCode;

    private String address;

    private int sync;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSync() {
        return sync;
    }

    public void setSync(int sync) {
        this.sync = sync;
    }

    public Order() {
    }

    public Order(int id, String orderCode, String address, int sync) {
        this.id = id;
        this.orderCode = orderCode;
        this.address = address;
        this.sync = sync;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderCode='" + orderCode + '\'' +
                ", address='" + address + '\'' +
                ", sync=" + sync +
                '}';
    }
}
