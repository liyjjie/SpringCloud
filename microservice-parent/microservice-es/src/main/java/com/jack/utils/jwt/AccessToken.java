package com.jack.utils.jwt;

/**
 * @author ：liyongjie
 * @ClassName ：AccessToken
 * @date ： 2019-11-27 15:17
 * @modified By：
 */
public class AccessToken {

    private Long uid;
    private String userKey;
    private String token;
    private Long expireTimestamp;

    public AccessToken() {
    }

    public Long getUid() {
        return this.uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUserKey() {
        return this.userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getExpireTimestamp() {
        return this.expireTimestamp;
    }

    public void setExpireTimestamp(Long expireTimestamp) {
        this.expireTimestamp = expireTimestamp;
    }

    public String toString() {
        return "AccessToken{uid=" + this.uid + ", userKey='" + this.userKey + '\'' + ", token='" + this.token + '\'' + ", expireTimestamp=" + this.expireTimestamp + '}';
    }
}
