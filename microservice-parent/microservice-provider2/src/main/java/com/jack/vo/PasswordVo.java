package com.jack.vo;

/**
 * @author ：liyongjie
 * @ClassName ：PasswordVo
 * @date ： 2020-10-14 10:03
 * @modified By：
 */
public class PasswordVo {

    private Long id;

    private String userName;

    private String password;

    private Integer version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
