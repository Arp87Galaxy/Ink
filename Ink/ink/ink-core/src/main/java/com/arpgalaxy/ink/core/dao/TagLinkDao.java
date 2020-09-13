package com.arpgalaxy.ink.core.dao;

import com.arpgalaxy.ink.core.entity.TagLinkEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 标签多对多维护表
 * 
 * @author arpgalaxy
 * @email 13605560342@163.com
 * @date 2020-09-05 14:47:37
 */
@Mapper
public interface TagLinkDao extends BaseMapper<TagLinkEntity> {
	
}
