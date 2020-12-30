package com.arpgalaxy.ink.common.utils.redis;

/**
 * @author arpgalaxy
 * @date 2020/10/3
 * @email 13605560342@163.com
 * @description
 */
public enum  RedisKeyPrefix {
    CAPTCHA("CAPTCHA::"),
    AUTHTOKEN("AUTHTOKEN::"),
    USER_TOKEN("USER:TOKEN::");
    private String prefix=null;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    private RedisKeyPrefix(String prefix){
        this.prefix = prefix;
    }
}
