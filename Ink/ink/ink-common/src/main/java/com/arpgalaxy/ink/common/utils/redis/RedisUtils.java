package com.arpgalaxy.ink.common.utils.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * @author arpgalaxy
 * @date 2020/10/13
 * @email 13605560342@163.com
 * @description
 */
@Component
public class RedisUtils {

    private Duration defaultExpire=Duration.ofMinutes(360);

    @Autowired
    private  RedisTemplate<String,Object> redisTemplate;


    public Duration getDefaultExpire() {
        return defaultExpire;
    }

    public void setDefaultExpire(Duration defaultExpire) {
        this.defaultExpire = defaultExpire;
    }

    /**
     * 默认过期时间
     * @param key
     * @param value
     * @return
     */
    public void set(String key,Object value){
        redisTemplate.opsForValue().set(key,value,getDefaultExpire());
    }
    public Object get(String key){
        return redisTemplate.opsForValue().get(key);
    }
    public boolean delete(String key){
        return redisTemplate.delete(key);
    }



}
