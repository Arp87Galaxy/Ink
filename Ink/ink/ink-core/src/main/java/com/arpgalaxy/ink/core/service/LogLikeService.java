package com.arpgalaxy.ink.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.arpgalaxy.ink.common.utils.PageUtils;
import com.arpgalaxy.ink.core.entity.LogLikeEntity;

import java.util.Map;

/**
 * 点赞日志
 *
 * @author arpgalaxy
 * @email 13605560342@163.com
 * @date 2020-09-05 14:47:37
 */
public interface LogLikeService extends IService<LogLikeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

