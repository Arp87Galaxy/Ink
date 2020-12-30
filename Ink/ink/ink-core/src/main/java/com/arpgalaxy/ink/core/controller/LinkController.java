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

import com.arpgalaxy.ink.core.entity.LinkEntity;
import com.arpgalaxy.ink.core.service.LinkService;
import com.arpgalaxy.ink.common.utils.PageUtils;
import com.arpgalaxy.ink.common.utils.response.R;



/**
 * 友链
 *
 * @author arpgalaxy
 * @email 13605560342@163.com
 * @date 2020-09-05 14:47:37
 */
@RestController
@RequestMapping("core/link")
public class LinkController {
    @Autowired
    private LinkService linkService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("core:link:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = linkService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("core:link:info")
    public R info(@PathVariable("id") Integer id){
		LinkEntity link = linkService.getById(id);

        return R.ok().put("link", link);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("core:link:save")
    public R save(@RequestBody LinkEntity link){
		linkService.save(link);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("core:link:update")
    public R update(@RequestBody LinkEntity link){
		linkService.updateById(link);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("core:link:delete")
    public R delete(@RequestBody Integer[] ids){
		linkService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
