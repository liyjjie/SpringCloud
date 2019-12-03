package com.jack.utils.exception;

import com.jack.utils.MessageReader;

/**
 * @author ：liyongjie
 * @ClassName ：ServiceException
 * @date ： 2019-11-27 14:03
 * @modified By：
 */
public class ServiceException extends RuntimeException {

    private Object[] params;
    private String errorCode;

    public ServiceException(String errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    public ServiceException(String errorCode, Object[] params, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.setParams(params);
    }

    public ServiceException(String errorCode) {
        this.errorCode = errorCode;
    }

    public ServiceException(String errorCode, Object... params) {
        this.setParams(params);
        this.errorCode = errorCode;
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public Object[] getParams() {
        return this.params;
    }

    private void setParams(Object... params) {
        this.params = params;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getMessage() {
        try {
            return MessageReader.getMessage(this.errorCode, this.params);
        } catch (Exception var2) {
            return "ServiceException getMessage fail";
        }
    }
}
