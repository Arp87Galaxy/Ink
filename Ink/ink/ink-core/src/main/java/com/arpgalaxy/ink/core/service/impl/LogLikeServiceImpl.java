package com.arpgalaxy.ink.core.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.arpgalaxy.ink.common.utils.PageUtils;
import com.arpgalaxy.ink.common.utils.Query;

import com.arpgalaxy.ink.core.dao.LogLikeDao;
import com.arpgalaxy.ink.core.entity.LogLikeEntity;
import com.arpgalaxy.ink.core.service.LogLikeService;


@Service("logLikeService")
public class LogLikeServiceImpl extends ServiceImpl<LogLikeDao, LogLikeEntity> implements LogLikeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LogLikeEntity> page = this.page(
                new Query<LogLikeEntity>().getPage(params),
                new QueryWrapper<LogLikeEntity>()
        );

        return new PageUtils(page);
    }

}