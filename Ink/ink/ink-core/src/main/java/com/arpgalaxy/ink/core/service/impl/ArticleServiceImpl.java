package com.arpgalaxy.ink.core.service.impl;

import com.arpgalaxy.ink.core.constants.RedisCacheNames;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.arpgalaxy.ink.common.utils.PageUtils;
import com.arpgalaxy.ink.common.utils.Query;

import com.arpgalaxy.ink.core.dao.ArticleDao;
import com.arpgalaxy.ink.core.entity.ArticleEntity;
import com.arpgalaxy.ink.core.service.ArticleService;


@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleDao, ArticleEntity> implements ArticleService {

    @Override
    @Cacheable(cacheNames = {RedisCacheNames.PageUtils},keyGenerator = "listCacheKeyGenerator")
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ArticleEntity> page = this.page(
                new Query<ArticleEntity>().getPage(params),
                new QueryWrapper<ArticleEntity>()
        );
        return new PageUtils(page);
    }

}