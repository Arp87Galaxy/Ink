package com.arpgalaxy.ink.common.utils.redis;

import javax.servlet.http.HttpSession;

/**
 * @author arpgalaxy
 * @date 2020/10/3
 * @email 13605560342@163.com
 * @description
 */
public class RedisKeyGenertor {
    public static String getCaptchaKeyBySessionId(HttpSession session){
        return RedisKeyPrefix.CAPTCHA.getPrefix()+session.getId();
    }
    public static String getAuthUserKey(String authToken){
        return RedisKeyPrefix.AUTHTOKEN.getPrefix()+authToken;
    }
    public static String getUserTokenKey(Long userId){
        return RedisKeyPrefix.USER_TOKEN.getPrefix()+userId;
    }
}
