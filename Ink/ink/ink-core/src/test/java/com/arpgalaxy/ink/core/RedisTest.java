package com.arpgalaxy.ink.core;

import com.arpgalaxy.ink.common.utils.Constant;
import com.arpgalaxy.ink.common.utils.PageUtils;
import com.arpgalaxy.ink.core.entity.ArticleEntity;
import com.arpgalaxy.ink.core.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
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

    @Test
    public void redisTemplateTest(){
        redisTemplate.opsForValue().set("a",new ArticleEntity());
        System.out.println(redisTemplate.opsForValue().get("a"));
    }

}
