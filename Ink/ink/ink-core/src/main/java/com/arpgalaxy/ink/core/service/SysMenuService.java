package com.arpgalaxy.ink.core.service;

import com.arpgalaxy.ink.core.entity.SysUserEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.arpgalaxy.ink.common.utils.PageUtils;
import com.arpgalaxy.ink.core.entity.SysMenuEntity;

import java.util.List;
import java.util.Map;

/**
 * 菜单管理
 *
 * @author arpgalaxy
 * @email 13605560342@163.com
 * @date 2020-09-05 14:47:37
 */
public interface SysMenuService extends IService<SysMenuEntity> {

    PageUtils queryPage(Map<String, Object> params);


    List<SysMenuEntity> queryMenuList(SysUserEntity sysUserEntity);
    List<SysMenuEntity> queryMenuTreeByUserId(SysUserEntity userEntity);
}

