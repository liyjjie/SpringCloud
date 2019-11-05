package com.jack.redis;

import redis.clients.jedis.JedisPool;

/**
 * @author ：liyongjie
 * @ClassName ：RedisUtils
 * @date ： 2019-11-05 15:53
 * @modified By：
 */
public class RedisUtils {
    public RedisUtils() {
    }

    public static JedisPool getJedisPool(String pool) {
        return RedisFactory.getInstance().getJedisPool(pool);
    }
}

