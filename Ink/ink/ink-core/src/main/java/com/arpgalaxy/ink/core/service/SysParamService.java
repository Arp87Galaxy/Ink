package com.arpgalaxy.ink.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.arpgalaxy.ink.common.utils.PageUtils;
import com.arpgalaxy.ink.core.entity.SysParamEntity;

import java.util.Map;

/**
 * 系统参数
 *
 * @author arpgalaxy
 * @email 13605560342@163.com
 * @date 2020-09-05 14:47:37
 */
public interface SysParamService extends IService<SysParamEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

