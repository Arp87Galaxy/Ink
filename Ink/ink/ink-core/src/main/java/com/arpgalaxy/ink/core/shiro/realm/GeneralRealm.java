package com.arpgalaxy.ink.core.shiro.realm;

import com.arpgalaxy.ink.common.shiro.token.InkAuthenticationToken;
import com.arpgalaxy.ink.core.dao.SysUserDao;
import com.arpgalaxy.ink.core.entity.SysRoleEntity;
import com.arpgalaxy.ink.core.entity.SysUserEntity;
import com.arpgalaxy.ink.core.service.LoginService;
import com.arpgalaxy.ink.core.service.SysUserService;
import org.apache.lucene.search.Collector;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author arpgalaxy
 * @date 2020/9/27
 * @email 13605560342@163.com
 * @description
 */
@Component
public class GeneralRealm extends AuthorizingRealm {
    @Autowired
    private LoginService loginService;
    @Autowired
    private SysUserService sysUserService;
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof InkAuthenticationToken;
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SysUserEntity sysUserEntity=(SysUserEntity)principals.getPrimaryPrincipal();
        Set<String> perms=sysUserService.queryPermissions(sysUserEntity);
        Set<String> roles = sysUserEntity.getRoleEntities().stream().map(sysRoleEntities -> sysRoleEntities.getRoleName()).collect(Collectors.toSet());

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(perms);
        simpleAuthorizationInfo.setRoles(roles);
        return simpleAuthorizationInfo;
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取token
        String tokenPrincipal = (String) token.getPrincipal();
        //验证token
        Long userId = loginService.isLogined(tokenPrincipal);
        if (userId == null) {
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }
        //查询用户信息
        SysUserEntity sysUserEntity = sysUserService.queryById(userId);
        return new SimpleAuthenticationInfo(sysUserEntity,tokenPrincipal,getName());
    }


}
