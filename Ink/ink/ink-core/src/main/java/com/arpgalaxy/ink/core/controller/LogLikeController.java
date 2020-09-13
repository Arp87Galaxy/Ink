package com.arpgalaxy.ink.core.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arpgalaxy.ink.core.entity.LogLikeEntity;
import com.arpgalaxy.ink.core.service.LogLikeService;
import com.arpgalaxy.ink.common.utils.PageUtils;
import com.arpgalaxy.ink.common.utils.R;



/**
 * 点赞日志
 *
 * @author arpgalaxy
 * @email 13605560342@163.com
 * @date 2020-09-05 14:47:37
 */
@RestController
@RequestMapping("core/loglike")
public class LogLikeController {
    @Autowired
    private LogLikeService logLikeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("core:loglike:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = logLikeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("core:loglike:info")
    public R info(@PathVariable("id") Long id){
		LogLikeEntity logLike = logLikeService.getById(id);

        return R.ok().put("logLike", logLike);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("core:loglike:save")
    public R save(@RequestBody LogLikeEntity logLike){
		logLikeService.save(logLike);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("core:loglike:update")
    public R update(@RequestBody LogLikeEntity logLike){
		logLikeService.updateById(logLike);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("core:loglike:delete")
    public R delete(@RequestBody Long[] ids){
		logLikeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
