package com.jack.vo;

/**
 * @author ：liyongjie
 * @ClassName ：GoodDetail
 * @date ： 2020-10-13 15:26
 * @modified By：
 */
public class GoodDetail {
    private Long id;

    private String skuCode;

    private Integer qty;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
}
