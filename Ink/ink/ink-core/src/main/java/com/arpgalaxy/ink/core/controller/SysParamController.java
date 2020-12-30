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

import com.arpgalaxy.ink.core.entity.SysParamEntity;
import com.arpgalaxy.ink.core.service.SysParamService;
import com.arpgalaxy.ink.common.utils.PageUtils;
import com.arpgalaxy.ink.common.utils.response.R;



/**
 * 系统参数
 *
 * @author arpgalaxy
 * @email 13605560342@163.com
 * @date 2020-09-05 14:47:37
 */
@RestController
@RequestMapping("core/sysparam")
public class SysParamController {
    @Autowired
    private SysParamService sysParamService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("core:sysparam:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sysParamService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("core:sysparam:info")
    public R info(@PathVariable("id") Long id){
		SysParamEntity sysParam = sysParamService.getById(id);

        return R.ok().put("sysParam", sysParam);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("core:sysparam:save")
    public R save(@RequestBody SysParamEntity sysParam){
		sysParamService.save(sysParam);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("core:sysparam:update")
    public R update(@RequestBody SysParamEntity sysParam){
		sysParamService.updateById(sysParam);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("core:sysparam:delete")
    public R delete(@RequestBody Long[] ids){
		sysParamService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
