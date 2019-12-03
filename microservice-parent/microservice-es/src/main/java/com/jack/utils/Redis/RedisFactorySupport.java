package com.jack.utils.Redis;

import redis.clients.jedis.JedisPool;

/**
 * @author ：liyongjie
 * @ClassName ：RedisFactorySupport
 * @date ： 2019-11-27 16:11
 * @modified By：
 */
public class RedisFactorySupport {

    public RedisFactorySupport() {
    }

    public void setJedisPool(String poll, JedisPool jedisPool) {
        RedisFactory.getInstance().pollMap.put(poll, jedisPool);
    }
}
