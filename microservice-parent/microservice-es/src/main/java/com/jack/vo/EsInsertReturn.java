package com.jack.vo;

/**
 * @author ：liyongjie
 * @ClassName ：EsInsertReturn
 * @date ： 2019-12-03 11:28
 * @modified By：
 */
public class EsInsertReturn {

    private Long id;

    private Long userId;

    private String searchContents;

    private Long createDate;

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

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

    @Override
    public String toString() {
        return "EsInsertReturn{" +
                "id=" + id +
                ", userId=" + userId +
                ", searchContents='" + searchContents + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
