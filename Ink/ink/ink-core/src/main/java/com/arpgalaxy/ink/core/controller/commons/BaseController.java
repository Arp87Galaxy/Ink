package com.arpgalaxy.ink.core.controller.commons;

import com.arpgalaxy.ink.core.entity.SysUserEntity;
import org.apache.shiro.SecurityUtils;

/**
 * @author arpgalaxy
 * @date 2020/10/16
 * @email 13605560342@163.com
 * @description
 */
public class BaseController {
    protected SysUserEntity getUser(){
        return (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
    }

    protected Long getUserId(){
        return getUser().getUserId();
    }
}
