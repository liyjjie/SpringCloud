package com.jack.utils.Redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ：liyongjie
 * @ClassName ：RedisProp
 * @date ： 2019-11-27 16:12
 * @modified By：
 */
//@ConfigurationProperties(prefix = "redis.order")
public class RedisProp {



    private String key;
    private String host;
    private String port;
    private String password;
    private String dbIndex;
    private String timeOut;
    private String maxActive;
    private String maxIdle;
    private String minIdle;
    private String maxWait;
    private String minEvictableIdleTimeMillis;
    private String numTestsPerEvictionRun;
    private String softMinEvictableIdleTimeMillis;
    private String testOnBorrow;
    private String testOnReturn;
    private String testWhileIdle;
    private String timeBetweenEvictionRunsMillis;
    private String whenExhaustedAction;

    public RedisProp() {
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return this.port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDbIndex() {
        return this.dbIndex;
    }

    public void setDbIndex(String dbIndex) {
        this.dbIndex = dbIndex;
    }

    public String getTimeOut() {
        return this.timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public String getMaxActive() {
        return this.maxActive;
    }

    public void setMaxActive(String maxActive) {
        this.maxActive = maxActive;
    }

    public String getMaxIdle() {
        return this.maxIdle;
    }

    public void setMaxIdle(String maxIdle) {
        this.maxIdle = maxIdle;
    }

    public String getMinIdle() {
        return this.minIdle;
    }

    public void setMinIdle(String minIdle) {
        this.minIdle = minIdle;
    }

    public String getMaxWait() {
        return this.maxWait;
    }

    public void setMaxWait(String maxWait) {
        this.maxWait = maxWait;
    }

    public String getMinEvictableIdleTimeMillis() {
        return this.minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(String minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public String getNumTestsPerEvictionRun() {
        return this.numTestsPerEvictionRun;
    }

    public void setNumTestsPerEvictionRun(String numTestsPerEvictionRun) {
        this.numTestsPerEvictionRun = numTestsPerEvictionRun;
    }

    public String getSoftMinEvictableIdleTimeMillis() {
        return this.softMinEvictableIdleTimeMillis;
    }

    public void setSoftMinEvictableIdleTimeMillis(String softMinEvictableIdleTimeMillis) {
        this.softMinEvictableIdleTimeMillis = softMinEvictableIdleTimeMillis;
    }

    public String getTestOnBorrow() {
        return this.testOnBorrow;
    }

    public void setTestOnBorrow(String testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public String getTestOnReturn() {
        return this.testOnReturn;
    }

    public void setTestOnReturn(String testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public String getTestWhileIdle() {
        return this.testWhileIdle;
    }

    public void setTestWhileIdle(String testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public String getTimeBetweenEvictionRunsMillis() {
        return this.timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(String timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public String getWhenExhaustedAction() {
        return this.whenExhaustedAction;
    }

    public void setWhenExhaustedAction(String whenExhaustedAction) {
        this.whenExhaustedAction = whenExhaustedAction;
    }

    public String toString() {
        return "RedisProp{key='" + this.key + '\'' + ", host='" + this.host + '\'' + ", port='" + this.port + '\'' + ", password='" + this.password + '\'' + ", dbIndex='" + this.dbIndex + '\'' + ", timeOut='" + this.timeOut + '\'' + ", maxActive='" + this.maxActive + '\'' + ", maxIdle='" + this.maxIdle + '\'' + ", minIdle='" + this.minIdle + '\'' + ", maxWait='" + this.maxWait + '\'' + ", minEvictableIdleTimeMillis='" + this.minEvictableIdleTimeMillis + '\'' + ", numTestsPerEvictionRun='" + this.numTestsPerEvictionRun + '\'' + ", softMinEvictableIdleTimeMillis='" + this.softMinEvictableIdleTimeMillis + '\'' + ", testOnBorrow='" + this.testOnBorrow + '\'' + ", testOnReturn='" + this.testOnReturn + '\'' + ", testWhileIdle='" + this.testWhileIdle + '\'' + ", timeBetweenEvictionRunsMillis='" + this.timeBetweenEvictionRunsMillis + '\'' + ", whenExhaustedAction='" + this.whenExhaustedAction + '\'' + '}';
    }
}
