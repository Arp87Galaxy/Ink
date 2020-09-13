package com.arpgalaxy.ink.core.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.arpgalaxy.ink.common.utils.PageUtils;
import com.arpgalaxy.ink.common.utils.Query;

import com.arpgalaxy.ink.core.dao.TagLinkDao;
import com.arpgalaxy.ink.core.entity.TagLinkEntity;
import com.arpgalaxy.ink.core.service.TagLinkService;


@Service("tagLinkService")
public class TagLinkServiceImpl extends ServiceImpl<TagLinkDao, TagLinkEntity> implements TagLinkService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TagLinkEntity> page = this.page(
                new Query<TagLinkEntity>().getPage(params),
                new QueryWrapper<TagLinkEntity>()
        );

        return new PageUtils(page);
    }

}