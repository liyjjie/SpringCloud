package com.jack.utils.execl;

import com.jack.utils.exception.ServiceException;

/**
 * @author ：liyongjie
 * @ClassName ：ExcelException
 * @date ： 2019-11-27 14:02
 * @modified By：
 */
public class ExcelException extends ServiceException {
    public ExcelException(String errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public ExcelException(String errorCode, Object[] params, Throwable cause) {
        super(errorCode, params, cause);
    }

    public ExcelException(String errorCode) {
        super(errorCode);
    }

    public ExcelException(String errorCode, Object... params) {
        super(errorCode, params);
    }

    public ExcelException(Throwable cause) {
        super(cause);
    }
}
