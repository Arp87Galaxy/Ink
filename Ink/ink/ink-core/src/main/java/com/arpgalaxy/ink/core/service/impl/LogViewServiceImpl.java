package com.arpgalaxy.ink.core.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.arpgalaxy.ink.common.utils.PageUtils;
import com.arpgalaxy.ink.common.utils.Query;

import com.arpgalaxy.ink.core.dao.LogViewDao;
import com.arpgalaxy.ink.core.entity.LogViewEntity;
import com.arpgalaxy.ink.core.service.LogViewService;


@Service("logViewService")
public class LogViewServiceImpl extends ServiceImpl<LogViewDao, LogViewEntity> implements LogViewService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LogViewEntity> page = this.page(
                new Query<LogViewEntity>().getPage(params),
                new QueryWrapper<LogViewEntity>()
        );

        return new PageUtils(page);
    }

}