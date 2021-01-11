package com.arpgalaxy.ink.core.service.impl;

import com.arpgalaxy.ink.core.dao.SysUserDao;
import com.arpgalaxy.ink.core.entity.SysUserEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.arpgalaxy.ink.common.utils.PageUtils;
import com.arpgalaxy.ink.common.utils.Query;

import com.arpgalaxy.ink.core.dao.SysMenuDao;
import com.arpgalaxy.ink.core.entity.SysMenuEntity;
import com.arpgalaxy.ink.core.service.SysMenuService;

import javax.annotation.Resource;


@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {

    @Resource
    private SysUserDao sysUserDao;
    @Resource
    private SysMenuDao sysMenuDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SysMenuEntity> page = this.page(
                new Query<SysMenuEntity>().getPage(params),
                new QueryWrapper<SysMenuEntity>()
        );

        return new PageUtils(page);
    }
    public List<SysMenuEntity> queryMenuList(SysUserEntity sysUserEntity){

        List<SysMenuEntity> sysMenuEntities;
        if (sysUserEntity.getRoleEntities().get(0).getRoleId()==0){
            return sysMenuEntities = sysMenuDao.selectList(null);
        }else {

            sysMenuEntities = sysUserEntity.getRoleEntities().stream()
                    .flatMap(roles -> roles.getSysMenuEntities().stream())
                    .collect(Collectors.toList());
            return sysMenuEntities;
        }
    }

    public List<SysMenuEntity> queryMenuTreeByUserId(SysUserEntity sysUserEntity){
        //查出所有菜单
        List<SysMenuEntity> sysMenuEntities = queryMenuList(sysUserEntity);

        if (sysMenuEntities != null) {
            List<SysMenuEntity> sysMenuTree = sysMenuEntities.stream()
                    .filter(sysMenuEntity -> sysMenuEntity.getParentId() == 0)
                    .map((pMenu) -> {
                        pMenu.setChildren(getChildren(pMenu, sysMenuEntities));
                        return pMenu;
                    })
                    .sorted(Comparator.comparing(SysMenuEntity::getOrderNum).reversed())
//                    .sorted((menu1, menu2)
//                            -> (menu1.getOrderNum() == null ? 0 : menu1.getOrderNum()) - (menu2.getOrderNum() == null ? 0 : menu2.getOrderNum()))
                    .collect(Collectors.toList());
            return sysMenuTree;
        }
        return null;
    }

    @Override
    public List queryMenuColumnNames() {
        return sysMenuDao.queryMenuColumnNames();
    }

    private List<SysMenuEntity> getChildren(SysMenuEntity pMenu, List<SysMenuEntity> sysMenuEntities) {
        List<SysMenuEntity> childrens = sysMenuEntities.stream()
                .filter(sysMenuEntity -> sysMenuEntity.getParentId()==pMenu.getMenuId())
                .map((menu)->{
                    menu.setChildren(getChildren(menu,sysMenuEntities));
                    return menu;
                })
                .sorted(Comparator.comparing(SysMenuEntity::getOrderNum))
//                .sorted((menu1,menu2)
//                        -> (menu1.getOrderNum()==null?0:menu1.getOrderNum())-(menu2.getOrderNum()==null?0:menu2.getOrderNum()))
                .collect(Collectors.toList());
        return childrens;
    }

}