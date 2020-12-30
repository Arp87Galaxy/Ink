package com.arpgalaxy.ink.core.config;

import com.arpgalaxy.ink.common.utils.PageUtils;
import com.arpgalaxy.ink.core.constants.RedisCacheNames;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.net.UnknownHostException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author arpgalaxy
 * @date 2020/9/14
 * @email 13605560342@163.com
 * @description redis 配置类
 */
@Configuration
@EnableCaching
@ComponentScan("com.arpgalaxy.ink.common.utils")
public class RedisConfig {

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory){
        return new RedisCacheManager(
                RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory),
                RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(1)).serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class))),
                getRedisCacheConfigurationMap()
        );
    }
    private Map<String, RedisCacheConfiguration> getRedisCacheConfigurationMap() {
        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>(1);
        //为特定的cache空间定制序列化，缓存时间等
        redisCacheConfigurationMap.put(RedisCacheNames.PageUtils, RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(1))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new Jackson2JsonRedisSerializer<PageUtils>(PageUtils.class))));

        redisCacheConfigurationMap.put("AUTHTOKEN::", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(2)));
        redisCacheConfigurationMap.put("a", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(2)));

        return redisCacheConfigurationMap;
    }
    //为手动操作缓存配置的redistemplate
    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Object,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        template.setDefaultSerializer(serializer);
        return template;
    }
    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        StringRedisTemplate template = new StringRedisTemplate();
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        template.setConnectionFactory(redisConnectionFactory);
        template.setDefaultSerializer(serializer);
        return template;
    }

}
