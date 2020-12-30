package com.arpgalaxy.ink.core.service.impl;

import com.arpgalaxy.ink.core.dao.SysMenuDao;
import com.arpgalaxy.ink.core.entity.SysMenuEntity;
import com.arpgalaxy.ink.core.service.SysMenuService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.arpgalaxy.ink.common.utils.PageUtils;
import com.arpgalaxy.ink.common.utils.Query;

import com.arpgalaxy.ink.core.dao.SysUserDao;
import com.arpgalaxy.ink.core.entity.SysUserEntity;
import com.arpgalaxy.ink.core.service.SysUserService;

import javax.annotation.Resource;


@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {

    @Resource
    private SysMenuDao sysMenuDao;
    @Autowired
    private SysMenuService sysMenuService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SysUserEntity> page = this.page(
                new Query<SysUserEntity>().getPage(params),
                new QueryWrapper<SysUserEntity>()
        );

        return new PageUtils(page);
    }


    @Override
    public SysUserEntity queryById(Long userId) {
        return baseMapper.selectById(userId);
    }

    @Override
    public Set<String> queryPermissions(SysUserEntity sysUserEntity) {
        List<SysMenuEntity> sysMenuEntities = sysMenuService.queryMenuList(sysUserEntity);

        return sysMenuEntities.stream()
                .filter(menu -> StringUtils.isNotBlank(menu.getPerms()))
                .map(menu -> menu.getPerms().trim())
                .flatMap(perms -> Arrays.stream(perms.split(",")))
                .collect(Collectors.toSet());
    }


}