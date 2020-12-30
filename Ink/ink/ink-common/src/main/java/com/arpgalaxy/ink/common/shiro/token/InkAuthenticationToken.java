package com.arpgalaxy.ink.common.shiro.token;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author arpgalaxy
 * @date 2020/10/13
 * @email 13605560342@163.com
 * @description
 */
public class InkAuthenticationToken implements AuthenticationToken {
    private String token;

    public InkAuthenticationToken(String token){
        this.token = token;
    }

    @Override
    public String getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
