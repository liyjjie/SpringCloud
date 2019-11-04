package com.jack.entity;

import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ：liyongjie
 * @ClassName ：Order
 * @date ： 2019-11-04 09:08
 * @modified By：
 */
@Entity
@Table(name="t_order")
public class Order implements Serializable {
    private int id;

    private String orderCode;

    private String address;

    private int sync;

    public static final String COL_ID="id";
    public static final String COL_ORDER_CODE="orderCode";
    public static final String COL_ADDRESS="address";
    public static final String COL_SYNC="sync";

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
    @Column(name = "order_code")
    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
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
    @Column(name="sync")
    public int getSync() {
        return sync;
    }

    public void setSync(int sync) {
        this.sync = sync;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (sync != order.sync) return false;
        if (orderCode != null ? !orderCode.equals(order.orderCode) : order.orderCode != null) return false;
        return address != null ? address.equals(order.address) : order.address == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (orderCode != null ? orderCode.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + sync;
        return result;
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
