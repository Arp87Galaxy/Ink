package com.arpgalaxy.ink.core;

import com.arpgalaxy.ink.core.entity.ArticleEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * @author arpgalaxy
 * @date 2020/9/14
 * @email 13605560342@163.com
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired

    private RedisCacheManager cacheManager;
    @Test
    public void redisTemplateTest(){
        Map<String, RedisCacheConfiguration> cacheConfigurations = cacheManager.getCacheConfigurations();
        redisTemplate.opsForValue().set("a",new ArticleEntity());
        System.out.println(redisTemplate.opsForValue().get("a"));
    }
    @Test
    public void mytest(){
        Long i =1002L;
        System.out.println("1"+i);
    }


}
