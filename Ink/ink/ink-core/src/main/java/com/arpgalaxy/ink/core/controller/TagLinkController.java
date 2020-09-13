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

import com.arpgalaxy.ink.core.entity.TagLinkEntity;
import com.arpgalaxy.ink.core.service.TagLinkService;
import com.arpgalaxy.ink.common.utils.PageUtils;
import com.arpgalaxy.ink.common.utils.R;



/**
 * 标签多对多维护表
 *
 * @author arpgalaxy
 * @email 13605560342@163.com
 * @date 2020-09-05 14:47:37
 */
@RestController
@RequestMapping("core/taglink")
public class TagLinkController {
    @Autowired
    private TagLinkService tagLinkService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("core:taglink:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = tagLinkService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("core:taglink:info")
    public R info(@PathVariable("id") Integer id){
		TagLinkEntity tagLink = tagLinkService.getById(id);

        return R.ok().put("tagLink", tagLink);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("core:taglink:save")
    public R save(@RequestBody TagLinkEntity tagLink){
		tagLinkService.save(tagLink);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("core:taglink:update")
    public R update(@RequestBody TagLinkEntity tagLink){
		tagLinkService.updateById(tagLink);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("core:taglink:delete")
    public R delete(@RequestBody Integer[] ids){
		tagLinkService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
