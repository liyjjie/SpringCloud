package com.jack.utils.jwt;

/**
 * @author ：liyongjie
 * @ClassName ：JwtException
 * @date ： 2019-11-27 15:19
 * @modified By：
 */
public class JwtException extends RuntimeException {
    public JwtException(String message) {
        super(message);
    }

    public JwtException(Throwable cause) {
        super(cause);
    }
}
