package com.jack.redis;

import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：liyongjie
 * @ClassName ：RedisFactory
 * @date ： 2019-11-05 15:59
 * @modified By：
 */
public class RedisFactory {

    Map<String, JedisPool> pollMap = new HashMap();
    private static final RedisFactory INSTANCE = new RedisFactory();

    private RedisFactory() {
    }

    public static RedisFactory getInstance() {
        return INSTANCE;
    }

    public JedisPool getJedisPool(String poll) {
        return (JedisPool)this.pollMap.get(poll);
    }
}
