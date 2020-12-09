package com.jack.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.RedisPassword;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;

import java.net.UnknownHostException;
import java.time.Duration;

/**
 * @author ：liyongjie
 * @ClassName ：RedisConf
 * @date ： 2020-12-08 14:18
 * @modified By：要重写RedisConnectionFactory方法 不然一直选择的是默认的redis配置
 */
@Configuration
public class RedisConf {
    @Value("${redis.order.host}")
    private String redisHost;

    @Value("${redis.order.port}")
    private int redisPort;

    @Value("${redis.order.password}")
    private String password;

    @Value("${redis.order.timeOut}")
    private int redisTimeout;

    @Value("${redis.order.password}")
    private String redisAuth;

    @Value("${redis.order.dbIndex}")
    private int redisDb;

    @Value("${redis.order.pool.maxActive}")
    private int maxActive;

    @Value("${redis.order.pool.maxWait}")
    private int maxWait;

    @Value("${redis.order.pool.maxIdle}")
    private int maxIdle;

    @Value("${redis.order.pool.minIdle}")
    private int minIdle;

    //    @Bean
    public RedisConnectionFactory connectionFactory() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(maxActive);
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMaxWaitMillis(maxWait);
        poolConfig.setMinIdle(minIdle);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(false);
        poolConfig.setTestWhileIdle(true);
/**
 * 添加以下方法时 要导入jar包 但目前导入的版本与spring boot 1.x版本冲突
 */
//        JedisClientConfiguration clientConfig = JedisClientConfiguration.builder()
//                .usePooling().poolConfig(poolConfig).and().readTimeout(Duration.ofMillis(redisTimeout)).build();

        // 单点redis
//        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
        // 哨兵redis
        // RedisSentinelConfiguration redisConfig = new RedisSentinelConfiguration();
        // 集群redis
//         RedisClusterConfiguration redisConfig = new RedisClusterConfiguration();
//        redisConfig.setHostName(redisHost);
//        redisConfig.setPassword(RedisPassword.of(redisAuth));
//        redisConfig.setPort(redisPort);
//        redisConfig.setDatabase(redisDb);

//        return new JedisConnectionFactory(redisConfig, clientConfig);
        return null;
    }
}
