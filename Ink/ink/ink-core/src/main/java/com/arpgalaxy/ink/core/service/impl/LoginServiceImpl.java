package com.arpgalaxy.ink.core.service.impl;

import com.arpgalaxy.ink.core.service.LoginService;
import com.google.code.kaptcha.Producer;
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
public class LoginServiceImpl implements LoginService {
    @Autowired
    private Producer producer;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public BufferedImage getCaptcha(HttpServletRequest request) {
        String text = producer.createText();
        HttpSession session = request.getSession();
        System.out.println(session.getId());
        redisTemplate.opsForValue().set("Captcha::"+session.getId(),text, Duration.ofMinutes(1));
        BufferedImage image = producer.createImage(text);
        return image;
    }
}
