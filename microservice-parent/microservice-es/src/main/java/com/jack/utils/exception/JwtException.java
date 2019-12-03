package com.jack.utils.exception;

/**
 * @author ：liyongjie
 * @ClassName ：JwtException
 * @date ： 2019-11-27 14:05
 * @modified By：
 */
public class JwtException extends RuntimeException{

    public JwtException(String message) {
        super(message);
    }

    public JwtException(Throwable cause) {
        super(cause);
    }
}
