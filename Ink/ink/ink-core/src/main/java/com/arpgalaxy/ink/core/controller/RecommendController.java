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

import com.arpgalaxy.ink.core.entity.RecommendEntity;
import com.arpgalaxy.ink.core.service.RecommendService;
import com.arpgalaxy.ink.common.utils.PageUtils;
import com.arpgalaxy.ink.common.utils.R;



/**
 * 推荐
 *
 * @author arpgalaxy
 * @email 13605560342@163.com
 * @date 2020-09-05 14:47:37
 */
@RestController
@RequestMapping("core/recommend")
public class RecommendController {
    @Autowired
    private RecommendService recommendService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("core:recommend:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = recommendService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("core:recommend:info")
    public R info(@PathVariable("id") Integer id){
		RecommendEntity recommend = recommendService.getById(id);

        return R.ok().put("recommend", recommend);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("core:recommend:save")
    public R save(@RequestBody RecommendEntity recommend){
		recommendService.save(recommend);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("core:recommend:update")
    public R update(@RequestBody RecommendEntity recommend){
		recommendService.updateById(recommend);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("core:recommend:delete")
    public R delete(@RequestBody Integer[] ids){
		recommendService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
