package com.jack.service.Impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jack.dao.HibernateSwagger;
import com.jack.entity.User;
import com.jack.redis.RedisUtils;
import com.jack.service.SwaggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author ：liyongjie
 * @ClassName ：SwaggerServiceImpl
 * @date ： 2019-11-05 14:39
 * @modified By：
 */
@Service
public class SwaggerServiceImpl implements SwaggerService {

    @Autowired
    private HibernateSwagger hibernateSwagger;

    private final ObjectMapper mapper = new ObjectMapper();

    @Transactional
    @Override
    public User getUserById(Integer id) {
        User user = hibernateSwagger.getUserById(id);
        return user;
    }

    @Override
    public void getRedisPool() throws Exception {
        JedisPool jedisPool = RedisUtils.getJedisPool("order");
        try (Jedis jedis = jedisPool.getResource()) {
            String key = "platform_ratio";
            String str = jedis.hget(key, "tingmimi");
            Map<String, BigDecimal> map = mapper.readValue(str, new TypeReference<Map<String, BigDecimal>>() {
            });
            for (Map.Entry<String,BigDecimal> temp: map.entrySet() ) {
                System.out.println(temp.getKey()+"--"+temp.getValue());
            }
        }
    }
}
