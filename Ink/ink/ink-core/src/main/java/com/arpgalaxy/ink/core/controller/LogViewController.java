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

import com.arpgalaxy.ink.core.entity.LogViewEntity;
import com.arpgalaxy.ink.core.service.LogViewService;
import com.arpgalaxy.ink.common.utils.PageUtils;
import com.arpgalaxy.ink.common.utils.response.R;



/**
 * 阅读日志
 *
 * @author arpgalaxy
 * @email 13605560342@163.com
 * @date 2020-09-05 14:47:37
 */
@RestController
@RequestMapping("core/logview")
public class LogViewController {
    @Autowired
    private LogViewService logViewService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("core:logview:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = logViewService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("core:logview:info")
    public R info(@PathVariable("id") Long id){
		LogViewEntity logView = logViewService.getById(id);

        return R.ok().put("logView", logView);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("core:logview:save")
    public R save(@RequestBody LogViewEntity logView){
		logViewService.save(logView);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("core:logview:update")
    public R update(@RequestBody LogViewEntity logView){
		logViewService.updateById(logView);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("core:logview:delete")
    public R delete(@RequestBody Long[] ids){
		logViewService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
