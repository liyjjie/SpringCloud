package com.jack.utils.Redis;



import com.jack.utils.RedisUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author ：liyongjie
 * @ClassName ：RedisLock
 * @date ： 2019-11-27 16:12
 * @modified By：
 */
public class RedisLock {


    private static final int DEFAULT_ACQUIRY_RESOLUTION_MILLIS = 100;

    /**
     * 加锁的Key
     */
    private String lockKey;

    /**
     * 锁超时时间，防止线程在入锁以后，无限的执行等待
     */
    private int expireMsecs = 10 * 1000;

    /**
     * 锁等待时间，防止线程饥饿
     */
    private int timeoutMsecs = 10 * 1000;

    /**
     * 是否已上锁
     */
    private volatile boolean locked = false;


    public RedisLock(String lockKey) {
        this.lockKey = lockKey;
    }

    public RedisLock(String lockKey, int expireMsecs, int timeoutMsecs) {
        this.lockKey = lockKey;
        this.expireMsecs = expireMsecs;
        this.timeoutMsecs = timeoutMsecs;
    }

    /**
     * 获得锁
     * <p/>
     * 实现思路: 主要是使用了redis 的setnx命令,缓存了锁.
     * reids缓存的key是锁的key,所有的共享, value是锁的到期时间(注意:这里把过期时间放在value了,没有时间上设置其超时时间)
     * 执行过程:
     * 1.通过setnx尝试设置某个key的值,成功(当前没有这个锁)则返回,成功获得锁
     * 2.锁已经存在则获取锁的到期时间,和当前时间比较,超时的话,则设置新的值
     */
    public synchronized boolean lock() throws InterruptedException {
        int timeout = timeoutMsecs;
        while (timeout >= 0) {
            JedisPool jedisPool = RedisUtils.getJedisPool(RedisConst.POOL_LOCK);
            try (Jedis jedis = jedisPool.getResource()) {
                long expires = System.currentTimeMillis() + expireMsecs + 1;
                String expiresStr = String.valueOf(expires); //锁到期时间
                if (jedis.setnx(lockKey, expiresStr) == 1) {
                    locked = true;
                    return true;
                }
                String currentValueStr = jedis.get(lockKey); //redis里的时间
                if (currentValueStr != null && Long.parseLong(currentValueStr) < System.currentTimeMillis()) {
                    //锁已超时
                    String oldValueStr = jedis.getSet(lockKey, expiresStr);
                    //获取上一个锁到期时间，并设置现在的锁到期时间，
                    //只有一个线程才能获取上一个线上的设置时间，因为jedis.getSet是同步的
                    if (oldValueStr != null && oldValueStr.equals(currentValueStr)) {
                        //防止误删（覆盖，因为key是相同的）了他人的锁——这里达不到效果，这里值会被覆盖，但是因为什么相差了很少的时间，所以可以接受
                        //[分布式的情况下]:如过这个时候，多个线程恰好都到了这里，但是只有一个线程的设置值和当前值相同，他才有权利获取锁
                        // lock acquired
                        locked = true;
                        return true;
                    }
                }
                timeout -= DEFAULT_ACQUIRY_RESOLUTION_MILLIS;
                //延迟100毫秒再获取锁
                Thread.sleep(DEFAULT_ACQUIRY_RESOLUTION_MILLIS);
            }
        }
        return false;
    }

    /**
     * 释放锁
     */
    public synchronized void unlock() {
        if (locked) {
            JedisPool jedisPool = RedisUtils.getJedisPool(RedisConst.POOL_LOCK);
            try (Jedis jedis = jedisPool.getResource()) {
                jedis.del(lockKey);
                locked = false;
            }
        }
    }

}
