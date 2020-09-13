package com.arpgalaxy.ink.core.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.arpgalaxy.ink.common.utils.PageUtils;
import com.arpgalaxy.ink.common.utils.Query;

import com.arpgalaxy.ink.core.dao.RecommendDao;
import com.arpgalaxy.ink.core.entity.RecommendEntity;
import com.arpgalaxy.ink.core.service.RecommendService;


@Service("recommendService")
public class RecommendServiceImpl extends ServiceImpl<RecommendDao, RecommendEntity> implements RecommendService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<RecommendEntity> page = this.page(
                new Query<RecommendEntity>().getPage(params),
                new QueryWrapper<RecommendEntity>()
        );

        return new PageUtils(page);
    }

}