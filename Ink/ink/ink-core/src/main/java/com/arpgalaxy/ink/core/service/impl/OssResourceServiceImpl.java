package com.arpgalaxy.ink.core.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.arpgalaxy.ink.common.utils.PageUtils;
import com.arpgalaxy.ink.common.utils.Query;

import com.arpgalaxy.ink.core.dao.OssResourceDao;
import com.arpgalaxy.ink.core.entity.OssResourceEntity;
import com.arpgalaxy.ink.core.service.OssResourceService;


@Service("ossResourceService")
public class OssResourceServiceImpl extends ServiceImpl<OssResourceDao, OssResourceEntity> implements OssResourceService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OssResourceEntity> page = this.page(
                new Query<OssResourceEntity>().getPage(params),
                new QueryWrapper<OssResourceEntity>()
        );

        return new PageUtils(page);
    }

}