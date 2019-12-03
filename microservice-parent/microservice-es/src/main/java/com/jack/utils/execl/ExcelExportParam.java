package com.jack.utils.execl;

import com.jack.utils.MessageReader;

/**
 * @author ：liyongjie
 * @ClassName ：ExcelExportParam
 * @date ： 2019-11-27 14:08
 * @modified By：
 */
public class ExcelExportParam {

    private static final int INTER_TYPE_EMPTY = 1;
    private static final int INTER_TYPE_RES_FILE = 2;
    private static final int RPOERTY_NAME_TYPE_BEAN_REFLECT = 3;
    private int titleNameType = 2;
    private String titleName;
    private int propertyNameType = 3;
    private String propertyName;
    private String nullValue = "";

    public ExcelExportParam(String titleName, String propertyName) {
        this.titleName = titleName;
        this.propertyName = propertyName;
    }

    public ExcelExportParam(int titleNameType, String titleName, String propertyName) {
        this.titleNameType = titleNameType;
        this.titleName = titleName;
        this.propertyName = propertyName;
    }

    public ExcelExportParam(int titleNameType, String titleName, int propertyNameType, String propertyName) {
        this.titleNameType = titleNameType;
        this.titleName = titleName;
        this.propertyNameType = propertyNameType;
        this.propertyName = propertyName;
    }

    public ExcelExportParam(int titleNameType, String titleName, int propertyNameType, String propertyName, String nullValue) {
        this.titleNameType = titleNameType;
        this.titleName = titleName;
        this.propertyNameType = propertyNameType;
        this.propertyName = propertyName;
        this.nullValue = nullValue;
    }

    public int getTitleNameType() {
        return this.titleNameType;
    }

    public void setTitleNameType(int titleNameType) {
        this.titleNameType = titleNameType;
    }

    public String getTitleName() {
        return this.titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public int getPropertyNameType() {
        return this.propertyNameType;
    }

    public void setPropertyNameType(int propertyNameType) {
        this.propertyNameType = propertyNameType;
    }

    public String getPropertyName() {
        return this.propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getNullValue() {
        return this.nullValue;
    }

    public void setNullValue(String nullValue) {
        this.nullValue = nullValue;
    }

    public String getTitle() {
        String title = null;
        switch (this.titleNameType) {
            case 1:
                title = this.titleName;
                break;
            case 2:
                title = MessageReader.getMessage(this.titleName);
        }

        return title == null ? this.nullValue : title;
    }

    public String getValue(Object pojo) {
        Object title = null;
        switch (this.propertyNameType) {
            case 3:
                title = BeanUtils.invokGetMethodChain(pojo, this.propertyName, ".", new Object[0], (Object) null) == null ? null : BeanUtils.invokGetMethodChain(pojo, this.propertyName, ".", new Object[0], (Object) null);
            default:
                return title == null ? this.nullValue : title.toString();
        }
    }
}
