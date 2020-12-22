package com.jack.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Objects;

/**
 * @author ：liyongjie
 * @ClassName ：SimpleRedisLock
 * @date ： 2020-12-22 10:40
 * @modified By：分布式 redis锁最新版
 */
public class SimpleRedisLock {

    private long expireTime;

    private String key;

    private boolean locked = false;

    @Autowired
    private StringRedisTemplate redisTemplate;

    public boolean tryLock(final String key) {
        this.key = key;
        long expire = 15000;
        this.expireTime = System.currentTimeMillis() + expire;
        return (Boolean) redisTemplate.execute((RedisCallback) connection -> {
            /*因为此处锁的是函数名，认为函数不会超过一个范围，且早晚会被再次调用重新获取到锁，因此不做key ttl超时*/
            Boolean acquire = connection.setNX(this.key.getBytes(), String.valueOf(this.expireTime).getBytes());
            if (acquire) {
                locked = true;
                connection.expire(this.key.getBytes(), 300);
                return true;
            } else {
                byte[] value = connection.get(this.key.getBytes());
                if (Objects.nonNull(value) && value.length > 0) {
                    long expireTime = Long.parseLong(new String(value));
                    if (expireTime < System.currentTimeMillis()) {
                        byte[] oldValue = connection.getSet(this.key.getBytes(), String.valueOf(this.expireTime).getBytes());
                        locked = Long.parseLong(new String(oldValue)) < this.expireTime;
                        connection.expire(this.key.getBytes(), 300);
                        return locked;
                    }
                }
            }
            return false;
        });
    }

    public void unlock() {
        /*使用LUA脚本保证get-del操作线程安全。*/
        if (locked) {

            String script = "if redis.call(\"get\",KEYS[1]) == ARGV[1] then return redis.call(\"del\",KEYS[1]) else return 0 end";
            Long execute = redisTemplate.execute((RedisConnection conn) -> conn.eval(script.getBytes(),
                    ReturnType.INTEGER, 1, key.getBytes(), String.valueOf(expireTime).getBytes()));
            locked = false;
        }
    }
}
