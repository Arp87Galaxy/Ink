package com.arpgalaxy.ink.core.dao;

import com.arpgalaxy.ink.core.entity.SysMenuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 菜单管理
 * 
 * @author arpgalaxy
 * @email 13605560342@163.com
 * @date 2020-09-05 14:47:37
 */
@Mapper
public interface SysMenuDao extends BaseMapper<SysMenuEntity> {

    List queryMenuColumnNames();
}
