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

# 3.shiro

## 1.关于拦截器链

tomcat的ApplicationFilterChain执行Filter 的doFilte方法，shiro中的**OncePerRequestFilter**的doFilter（）接受ApplicationFilterChain的实例作为参数。方法执行，如果shiro的filter都被没有执行完，就会进入AbstractShiroFilter的doFilterInternal执行自己的拦截器链。如果shiro的filter都被执行完，才会执行ApplicationFilterChain的其他filter。

## 2.关于shiro自己管理session

简单来说，请求进来，会被shiro的**OncePerRequestFilter**过滤器拦截，对传进来的请求包装成ShiroHttpServletRequest，进入ShiroHttpServletRequest的逻辑处理，第一次请求会先**securityManager.start(sessionContext);**再赋值给一个ShiroHttpSession(继承javax.servlet.httpHttpSession）实例。

# 4. 问题

## 1.session不同

https://www.cnblogs.com/diandianquanquan/p/10607102.html

现象：Spring boot验证码接口与登录接口的sessionid不一致

解决方法：

WebConfig中添加如下代码：

```
  @Override



    public void addCorsMappings(CorsRegistry registry) {



        registry.addMapping("/**")



                .allowedOrigins("*")



                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")



                .allowCredentials(true)



                .maxAge(3600)



                .allowedHeaders("*");



    }
```

WebConfig完整代码如下：

```
@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    //    registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {



        registry.addMapping("/**")



                .allowedOrigins("*")



                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")



                .allowCredentials(true)



                .maxAge(3600)



                .allowedHeaders("*");



    }



 



}
```

前端在ajax中添加对应代码如下

```
xhrFields: {



                    withCredentials: true



                }
```

ajax完整代码如下：

```
login: function (event) {



			var data = "username="+vm.username+"&password="+vm.password+"&captcha="+vm.captcha;



			$.ajax({



				type: "POST",



			    url: "http://localhost:8085/cast_lbs/user/login",



			    data: data,



			    dataType: "json",



			    success: function(result){



					if(result.code == 0){//登录成功



						parent.location.href ='index.html';



					}else{



						vm.error = true;



						vm.errorMsg = result.msg;



						



						vm.refreshCode();



					}



				},



                xhrFields: {



                    withCredentials: true



                }



			});



		}
```

**相关原理参考：阮一峰的网络日志->跨域资源共享 CORS 详解**  http://www.ruanyifeng.com/blog/2016/04/cors.html

### 问题描述：

记录一个很小但又搞得我蛋疼的问题：我在登陆之后将token和用户数据存到session当中，可当我在后面的接口取值时，为null

### 解决过程：

接下来在调试的时候发现每次请求sessionid不一致，期初我还是以为是跨域造成的，于是按照以往的方式配置拦截器设置了这样一段

```java
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));//支持跨域请求



        response.setHeader("Access-Control-Allow-Headers", "Authorization, Origin, X-Requested-With, Content-Type, Accept,Access-Token");



        response.setHeader("Access-Control-Allow-Methods","PUT,POST,GET,DELETE,OPTIONS,PATCH");



        response.setHeader("Access-Control-Allow-Credentials","true");//是否支持cookie跨域
```

在前端配置了

```javascript
axios.defaults.withCredentials = true;// 允许跨域携带cookie
```

重新启动项目刷新访问，发现依然不行，接下来我又把Origin写死成前端服务地址，可是还是不行......网上各种百度，答案跟我以上几乎一样，内心慌张，汗流一地...但还是得继续排查呀

继续一边百度一边打开了昨天研究的一个小demo，也用到了session中的数据，可那demo是能取到值的，我对比了连着之间的配置，Cors相关的配置都是差不多的呀，前端结构也一样的，这就奇怪了。

继续仔细对比，发现demo没有配置api项目名，也就是

```java
server:



  servlet:



    #api前缀（项目名）



    context-path: /



  #服务端口



  port: 8080
```

而我这边是有配置项目名的，于是，我也把yaml和前端的项目名去掉了，结果还真就好了！！！

### 初步解决方案

可是配置在分模块开发nginx反向代理的场景下不配置项目名是不行的呀，可是又没有解决办法，情急之下想到了**手动去修改Cookies的JSESSIONID的值**，于是尝试**在登陆之后将sessionid手动写入了Cookies的JSESSIONID**，结果发现真的行得通！

可是仔细想想，这样做的话就把定sessionid的起始点写死了，如果要在登陆前请求其他需要用到session的接口，那不就GG了吗，如果有变动我们根本不确定要调的第一个接口是哪个，虽然我们开发时是知道的，但作为具有强迫症的开发者得我，是非常不容许“定死”“写死”这样的字眼出现在我的字典里的！！！

### 最终解决方案

知道是多了项目名的问题后，于是我又顺着“项目名导致session数据丢失”的思路去逛帖子，结果真发现了国外一老哥的一篇帖子！

模模糊糊看懂他表达的意思是将Vue代理中的的名字改为接口根路径（也就是后台配置的api项目名），抱着试一试的心态去改了

```javascript
proxyTable: {



      '/Api': {



        target: 'http://192.168.1.133:8080/ttic/api/',



        changeOrigin: true,



        pathRewrite: {



          '^/Api': '/'



        }



      }



    },



 



改为以下



proxyTable: {



      '/ttic': {



        target: 'http://192.168.1.133:8080/ttic/api/',



        changeOrigin: true,



        pathRewrite: {



          '^/ttic': '/'



        }



      }



    },
```

重新编译后，重新进入看腻了的登录界面，啪！按下回车键，大功告成！！！进入首页了！！！至此终于解决了困扰了我一个多小时的问题！！！赶紧打开CSDN记录一下！