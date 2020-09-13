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

import com.arpgalaxy.ink.core.entity.OssResourceEntity;
import com.arpgalaxy.ink.core.service.OssResourceService;
import com.arpgalaxy.ink.common.utils.PageUtils;
import com.arpgalaxy.ink.common.utils.R;



/**
 * 云存储资源表
 *
 * @author arpgalaxy
 * @email 13605560342@163.com
 * @date 2020-09-05 14:47:37
 */
@RestController
@RequestMapping("core/ossresource")
public class OssResourceController {
    @Autowired
    private OssResourceService ossResourceService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("core:ossresource:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = ossResourceService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("core:ossresource:info")
    public R info(@PathVariable("id") Integer id){
		OssResourceEntity ossResource = ossResourceService.getById(id);

        return R.ok().put("ossResource", ossResource);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("core:ossresource:save")
    public R save(@RequestBody OssResourceEntity ossResource){
		ossResourceService.save(ossResource);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("core:ossresource:update")
    public R update(@RequestBody OssResourceEntity ossResource){
		ossResourceService.updateById(ossResource);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("core:ossresource:delete")
    public R delete(@RequestBody Integer[] ids){
		ossResourceService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
