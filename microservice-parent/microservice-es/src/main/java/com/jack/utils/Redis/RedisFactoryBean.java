package com.jack.utils.Redis;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author ：liyongjie
 * @ClassName ：RedisFactoryBean
 * @date ： 2019-11-27 16:11
 * @modified By：
 */
public class RedisFactoryBean implements InitializingBean, DisposableBean {

    private static final Logger logger = LoggerFactory.getLogger(RedisFactoryBean.class);
    private List<String> poolKeys = new ArrayList();
    private List<RedisProp> redisProps;

    public RedisFactoryBean() {
    }

    public void afterPropertiesSet() throws Exception {
        try {
            if (this.redisProps == null) {
                return;
            }

            RedisFactorySupport redisFactorySupport = new RedisFactorySupport();
            Iterator var2 = this.redisProps.iterator();

            while (var2.hasNext()) {
                RedisProp prop = (RedisProp) var2.next();
                if (this.poolKeys.contains(prop.getKey())) {
                    throw new RuntimeException(String.format("poolKey[%s]已经存在", prop.getKey()));
                }

                this.poolKeys.add(prop.getKey());
                String host = prop.getHost();
                String port = prop.getPort();
                String timeout = prop.getTimeOut();
                String password = prop.getPassword();
                if (StringUtils.isBlank(password)) {
                    password = null;
                }

                String dbIndex = prop.getDbIndex();
                String maxActive = prop.getMaxActive();
                String maxIdle = prop.getMaxIdle();
                String maxWait = prop.getMaxWait();
                String minEvictableIdleTimeMillis = prop.getMinEvictableIdleTimeMillis();
                String minIdle = prop.getMinIdle();
                String numTestsPerEvictionRun = prop.getNumTestsPerEvictionRun();
                String softMinEvictableIdleTimeMillis = prop.getSoftMinEvictableIdleTimeMillis();
                String testOnBorrow = prop.getTestOnBorrow();
                String testOnReturn = prop.getTestOnReturn();
                String testWhileIdle = prop.getTestWhileIdle();
                String timeBetweenEvictionRunsMillis = prop.getTimeBetweenEvictionRunsMillis();
                String whenExhaustedAction = prop.getWhenExhaustedAction();
                JedisPoolConfig config = new JedisPoolConfig();
                if (StringUtils.isNotBlank(maxActive) && StringUtils.isNumeric(maxActive)) {
                    config.setMaxTotal(Integer.parseInt(maxActive));
                }

                if (StringUtils.isNotBlank(maxIdle) && StringUtils.isNumeric(maxIdle)) {
                    config.setMaxIdle(Integer.parseInt(maxIdle));
                }

                if (StringUtils.isNotBlank(maxWait) && StringUtils.isNumeric(maxWait)) {
                    config.setMaxWaitMillis((long) Integer.parseInt(maxWait));
                }

                if (StringUtils.isNotBlank(minEvictableIdleTimeMillis) && StringUtils.isNumeric(minEvictableIdleTimeMillis)) {
                    config.setMinEvictableIdleTimeMillis((long) Integer.parseInt(minEvictableIdleTimeMillis));
                }

                if (StringUtils.isNotBlank(minIdle) && StringUtils.isNumeric(minIdle)) {
                    config.setMinIdle(Integer.parseInt(minIdle));
                }

                if (StringUtils.isNotBlank(numTestsPerEvictionRun) && StringUtils.isNumeric(numTestsPerEvictionRun)) {
                    config.setNumTestsPerEvictionRun(Integer.parseInt(numTestsPerEvictionRun));
                }

                if (StringUtils.isNotBlank(softMinEvictableIdleTimeMillis) && StringUtils.isNumeric(softMinEvictableIdleTimeMillis)) {
                    config.setSoftMinEvictableIdleTimeMillis((long) Integer.parseInt(softMinEvictableIdleTimeMillis));
                }

                if (StringUtils.isNotBlank(testOnBorrow)) {
                    config.setTestOnBorrow(Boolean.valueOf(testOnBorrow));
                }

                if (StringUtils.isNotBlank(testOnReturn)) {
                    config.setTestOnReturn(Boolean.valueOf(testOnReturn));
                }

                if (StringUtils.isNotBlank(testWhileIdle)) {
                    config.setTestWhileIdle(Boolean.valueOf(testWhileIdle));
                }

                if (StringUtils.isNotBlank(timeBetweenEvictionRunsMillis) && StringUtils.isNumeric(timeBetweenEvictionRunsMillis)) {
                    config.setTimeBetweenEvictionRunsMillis((long) Integer.parseInt(timeBetweenEvictionRunsMillis));
                }

                if (StringUtils.isNotBlank(whenExhaustedAction) && StringUtils.isNumeric(whenExhaustedAction)) {
                    config.setBlockWhenExhausted(Boolean.valueOf(whenExhaustedAction));
                }

                JedisPool jedisPool = new JedisPool(config, host, Integer.parseInt(port.trim()), Integer.parseInt(timeout.trim()), password, Integer.parseInt(dbIndex.trim()));
                redisFactorySupport.setJedisPool(prop.getKey(), jedisPool);
                logger.info(String.format("JedisPool[%s] is init,props is %s", prop.getKey(), prop.toString()));
            }

            logger.info(String.format("Redis init finish, pools is %s", this.poolKeys.toString()));
        } catch (Exception var23) {
            logger.error(ExceptionUtils.getStackTrace(var23));
        }

    }

    public void destroy() throws Exception {
        logger.info("RedisFactoryBean is destroy...");
        Iterator var1 = this.poolKeys.iterator();

        while (var1.hasNext()) {
            String poolKey = (String) var1.next();
            JedisPool jedisPool = RedisFactory.getInstance().getJedisPool(poolKey);
            if (jedisPool != null) {
                jedisPool.destroy();
            }
        }

    }

    public void setRedisProps(List<RedisProp> redisProps) {
        this.redisProps = redisProps;
    }
}
