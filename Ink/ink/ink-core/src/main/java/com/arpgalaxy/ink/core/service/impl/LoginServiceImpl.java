package com.arpgalaxy.ink.core.service.impl;

import com.arpgalaxy.ink.common.exception.StringException;
import com.arpgalaxy.ink.common.utils.redis.RedisKeyGenertor;
import com.arpgalaxy.ink.common.utils.redis.RedisUtils;
import com.arpgalaxy.ink.common.utils.TokenGenerator;
import com.arpgalaxy.ink.core.service.LoginService;
import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.time.Duration;

/**
 * @author arpgalaxy
 * @date 2020/9/15
 * @email 13605560342@163.com
 * @description
 */
@Service("loginService")
@Slf4j
public class LoginServiceImpl implements LoginService {
    @Autowired
    private Producer producer;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisUtils redisUtils;
    @Override
    public BufferedImage getCaptcha(HttpServletRequest request, String cap_count) {
        String text = producer.createText();
        HttpSession session = request.getSession();
        System.out.println(session.getId());
//        redisTemplate.opsForValue().set(RedisKeyGenertor.getCaptchaKeyBySessionId(session),text, Duration.ofMinutes(3));
        redisUtils.set(RedisKeyGenertor.getCaptchaKeyBySessionId(session),text);
        BufferedImage image = producer.createImage(text);
        return image;
    }

    @Override
    public boolean validate(HttpServletRequest request, String cap_code) {
        if (StringUtils.isBlank(cap_code)){
            throw new StringException("cap_code is blank.");
        }
        HttpSession session = request.getSession();
        String captcha = (String)redisTemplate.opsForValue().get(RedisKeyGenertor.getCaptchaKeyBySessionId(session));

        if (StringUtils.isEmpty(captcha)){
            log.info("验证码过期.........................");
            return false;
        }
        if (!cap_code.equals(captcha)) {
            return false;
        }

        redisTemplate.delete(RedisKeyGenertor.getCaptchaKeyBySessionId(session));
        return true;
    }

    @Override

    public String auth(Long userId) {
        String authToken = TokenGenerator.generateValue();
        //是否已经认证
        String userToken = (String)redisTemplate.opsForValue().get(RedisKeyGenertor.getUserTokenKey(userId));
        if (!StringUtils.isEmpty(userToken)){
            //删除原来缓存的token信息
            log.info(userToken);
            boolean delete = redisUtils.delete(RedisKeyGenertor.getAuthUserKey(userToken));
            if (delete){
                log.info("登录认证中重复登录删除成功");
            }else{
                log.info("登录认证中重复登录删除失败");
            }
        }
        redisTemplate.opsForValue().set(RedisKeyGenertor.getUserTokenKey(userId),authToken,Duration.ofMinutes(300));
        redisTemplate.opsForValue().set(RedisKeyGenertor.getAuthUserKey(authToken),userId,Duration.ofMinutes(300));
        return authToken;
    }

    @Override
    public Long isLogined(String authToken) {
        try {
            Integer userId = (Integer)redisTemplate.opsForValue().get(RedisKeyGenertor.getAuthUserKey(authToken));
            String token = (String) redisTemplate.opsForValue().get(RedisKeyGenertor.getUserTokenKey(userId.longValue()));
            if (userId ==null || token ==null) return null;
            return userId.longValue();
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public boolean logout(String token) {
        Integer userId = (Integer)redisUtils.get(RedisKeyGenertor.getAuthUserKey(token));
        boolean deleteUser = redisUtils.delete(RedisKeyGenertor.getAuthUserKey(token));
        boolean deleteToken = redisUtils.delete(RedisKeyGenertor.getUserTokenKey(userId.longValue()));
        if (deleteToken==true && deleteUser==true){
            return true;
        }else{
            return false;
        }


    }


}
