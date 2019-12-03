package com.jack.utils.Redis;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：liyongjie
 * @ClassName ：RedisConfiguration
 * @date ： 2019-11-27 16:13
 * @modified By：
 */
@Configuration
public class RedisConfiguration implements EnvironmentAware {

    private Environment environment;

    public RedisConfiguration() {
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public RedisFactoryBean redisFactoryBean() throws SQLException {
        String redisKeyStr = this.environment.getProperty("redis.keys");
        if (StringUtils.isBlank(redisKeyStr)) {
            return new RedisFactoryBean();
        } else {
            String[] keys = redisKeyStr.split(",");
            List<RedisProp> redisProps = new ArrayList();
            String[] var4 = keys;
            int var5 = keys.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                String key = var4[var6];
                RedisProp redisProp = new RedisProp();
                redisProp.setKey(key);
                redisProp.setHost(this.environment.getProperty("redis." + key + ".host"));
                redisProp.setPort(this.environment.getProperty("redis." + key + ".port"));
                redisProp.setPassword(this.environment.getProperty("redis." + key + ".password"));
                redisProp.setDbIndex(this.environment.getProperty("redis." + key + ".dbIndex"));
                redisProp.setTimeOut(this.environment.getProperty("redis." + key + ".timeOut"));
                redisProp.setMaxActive(this.environment.getProperty("redis." + key + ".pool.maxActive"));
                redisProp.setMaxIdle(this.environment.getProperty("redis." + key + ".pool.maxIdle"));
                redisProp.setMinIdle(this.environment.getProperty("redis." + key + ".pool.minIdle"));
                redisProp.setMaxWait(this.environment.getProperty("redis." + key + ".pool.maxWait"));
                redisProp.setTestOnBorrow(this.environment.getProperty("redis." + key + ".pool.testOnBorrow"));
                redisProps.add(redisProp);
            }

            RedisFactoryBean redisFactoryBean = new RedisFactoryBean();
            redisFactoryBean.setRedisProps(redisProps);
            return redisFactoryBean;
        }
    }
}
