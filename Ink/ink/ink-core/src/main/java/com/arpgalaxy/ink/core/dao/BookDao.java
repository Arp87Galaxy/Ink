package com.arpgalaxy.ink.core.dao;

import com.arpgalaxy.ink.core.entity.BookEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 图书表
 * 
 * @author arpgalaxy
 * @email 13605560342@163.com
 * @date 2020-09-05 14:47:37
 */
@Mapper
public interface BookDao extends BaseMapper<BookEntity> {
	
}
