package com.jack.utils;

import com.jack.utils.Redis.RedisFactory;
import redis.clients.jedis.JedisPool;

/**
 * @author ：liyongjie
 * @ClassName ：RedisUtils
 * @date ： 2019-11-27 16:12
 * @modified By：
 */
public class RedisUtils {

    public RedisUtils() {
    }

    public static JedisPool getJedisPool(String pool) {
        return RedisFactory.getInstance().getJedisPool(pool);
    }
}
