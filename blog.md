# 1.RedisConfig



```java
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
```

# 2.vue 

## 1.引入axios

## 2.封装axios

- 一、引入 js-cookie
  - [1、简介](https://www.cnblogs.com/l-y-h/p/12955001.html#_label0_0)
  - [2、token](https://www.cnblogs.com/l-y-h/p/12955001.html#_label0_1)
  - [3、安装、引入 js-cookie](https://www.cnblogs.com/l-y-h/p/12955001.html#_label0_2)
- 二、引入、封装 axios
  - [1、简介](https://www.cnblogs.com/l-y-h/p/12955001.html#_label1_0)
  - [2、封装要求](https://www.cnblogs.com/l-y-h/p/12955001.html#_label1_1)
  - [3、引入 axios](https://www.cnblogs.com/l-y-h/p/12955001.html#_label1_2)
- 三、引入、封装 mock
  - [1、简介](https://www.cnblogs.com/l-y-h/p/12955001.html#_label2_0)
  - [2、封装要求](https://www.cnblogs.com/l-y-h/p/12955001.html#_label2_1)
  - [3、引入 mock](https://www.cnblogs.com/l-y-h/p/12955001.html#_label2_2)