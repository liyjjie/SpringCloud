package com.jack.vo;

/**
 * @author ：liyongjie
 * @ClassName ：EsUpdateVo
 * @date ： 2019-12-10 10:24
 * @modified By：
 */
public class EsUpdateVo {

    private String id;

    private Long userId;

    private String searchContents;

    private Long createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
        return "EsUpdateVo{" +
                "id='" + id + '\'' +
                ", userId=" + userId +
                ", searchContents='" + searchContents + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
