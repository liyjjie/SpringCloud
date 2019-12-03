package com.jack.utils.pages;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author ：liyongjie
 * @ClassName ：Pages
 * @date ： 2019-11-27 15:50
 * @modified By：
 */
@ApiModel(
        value = "Pages",
        description = "分页类"
)
public class Pages {
    public static final int DEFAULT_PAGE_SIZE = 10;
    @ApiModelProperty("每页行数")
    private int pageSize = 10;
    @ApiModelProperty(
            value = "返回总行数",
            hidden = true
    )
    private long totalRow = 0L;
    @ApiModelProperty("当前页")
    private int currentPage = 1;

    public Pages() {
    }

    public Pages(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getCurrentPage() {
        return this.currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalRow() {
        return this.totalRow;
    }

    public void setTotalRow(long totalRow) {
        this.totalRow = totalRow;
    }

    public int getTotalPage() {
        int totalPage = (int)((this.getTotalRow() - 1L) / (long)this.getPageSize() + 1L);
        return totalPage;
    }

    public int getFirstRow() {
        return this.getPageSize() * (this.getCurrentPage() - 1);
    }

    public int getLastRow() {
        return this.getPageSize() * this.getCurrentPage();
    }

    public String toString() {
        return "Pages{pageSize=" + this.pageSize + ", totalRow=" + this.totalRow + ", currentPage=" + this.currentPage + '}';
    }
}