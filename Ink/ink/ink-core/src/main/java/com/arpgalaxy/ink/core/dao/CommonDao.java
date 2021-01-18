package com.arpgalaxy.ink.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface CommonDao {
    List<String > queryMenuColumnNames(String tableName);
}
