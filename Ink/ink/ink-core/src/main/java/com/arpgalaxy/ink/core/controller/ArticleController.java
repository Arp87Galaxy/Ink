package com.arpgalaxy.ink.core.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arpgalaxy.ink.core.entity.ArticleEntity;
import com.arpgalaxy.ink.core.service.ArticleService;
import com.arpgalaxy.ink.common.utils.PageUtils;
import com.arpgalaxy.ink.common.utils.R;



/**
 * 文章
 *
 * @author arpgalaxy
 * @email 13605560342@163.com
 * @date 2020-09-05 14:47:37
 * @description  文章
 */
@RestController
@RequestMapping("core/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("core:article:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = articleService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("core:article:info")
    public R info(@PathVariable("id") Integer id){
		ArticleEntity article = articleService.getById(id);

        return R.ok().put("article", article);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("core:article:save")
    public R save(@RequestBody ArticleEntity article){
		articleService.save(article);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("core:article:update")
    public R update(@RequestBody ArticleEntity article){
		articleService.updateById(article);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("core:article:delete")
    public R delete(@RequestBody Integer[] ids){
		articleService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
