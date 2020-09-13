package com.arpgalaxy.ink.core.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.arpgalaxy.ink.common.utils.PageUtils;
import com.arpgalaxy.ink.common.utils.Query;

import com.arpgalaxy.ink.core.dao.SysParamDao;
import com.arpgalaxy.ink.core.entity.SysParamEntity;
import com.arpgalaxy.ink.core.service.SysParamService;


@Service("sysParamService")
public class SysParamServiceImpl extends ServiceImpl<SysParamDao, SysParamEntity> implements SysParamService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SysParamEntity> page = this.page(
                new Query<SysParamEntity>().getPage(params),
                new QueryWrapper<SysParamEntity>()
        );

        return new PageUtils(page);
    }

}