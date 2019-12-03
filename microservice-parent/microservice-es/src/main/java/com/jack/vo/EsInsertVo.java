package com.jack.vo;

import io.searchbox.annotations.JestId;

import java.io.Serializable;

/**
 * @author ：liyongjie
 * @ClassName ：EsInsertVo
 * @date ： 2019-11-29 15:29
 * @modified By：
 */
public class EsInsertVo implements Serializable {
    private static final long serialVersionUID = 9086588908563096157L;

    @JestId
    private Long id;

    private Long userId;

    private String searchContents;

    private Long createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSearchContents() {
        return searchContents;
    }

    public void setSearchContents(String searchContents) {
        this.searchContents = searchContents;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "EsInsertVo{" +
                "id=" + id +
                ", userId=" + userId +
                ", searchContents='" + searchContents + '\'' +
                ", createDate=" + createDate +
                '}';
    }

    public EsInsertReturn toVo(){
        EsInsertReturn esInsertReturn=new EsInsertReturn();
        esInsertReturn.setId(this.getId());
        esInsertReturn.setCreateDate(this.getCreateDate());
        esInsertReturn.setSearchContents(this.getSearchContents());
        esInsertReturn.setUserId(this.getUserId());
        return esInsertReturn;
    }

}
