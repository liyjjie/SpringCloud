package com.jack.vo;

import java.util.List;

/**
 * @author ：liyongjie
 * @ClassName ：OrderVo
 * @date ： 2020-10-13 15:24
 * @modified By：
 */
public class OrderVo {
    private Long id;

    private String orderCode;

    private String address;

    private Integer sync;

    private List<GoodDetail> goodDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Integer getSync() {
        return sync;
    }

    public void setSync(Integer sync) {
        this.sync = sync;
    }

    public List<GoodDetail> getGoodDetails() {
        return goodDetails;
    }

    public void setGoodDetails(List<GoodDetail> goodDetails) {
        this.goodDetails = goodDetails;
    }
}
