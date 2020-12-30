package com.arpgalaxy.ink.core.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author arpgalaxy
 * @date 2020/9/15
 * @email 13605560342@163.com
 * @description
 */
@Configuration
public class CacheConfig {

    @Bean("listCacheKeyGenerator")
    public KeyGenerator listCacheKeyGenerator(){
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                System.out.println(target.getClass().getName());
                return target.getClass().getName();
            }
        };
    }
}
