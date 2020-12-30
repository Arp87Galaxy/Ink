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

import com.arpgalaxy.ink.core.entity.BookSenseEntity;
import com.arpgalaxy.ink.core.service.BookSenseService;
import com.arpgalaxy.ink.common.utils.PageUtils;
import com.arpgalaxy.ink.common.utils.response.R;



/**
 * 读后感
 *
 * @author arpgalaxy
 * @email 13605560342@163.com
 * @date 2020-09-05 14:47:37
 */
@RestController
@RequestMapping("core/booksense")
public class BookSenseController {
    @Autowired
    private BookSenseService bookSenseService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("core:booksense:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = bookSenseService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("core:booksense:info")
    public R info(@PathVariable("id") Integer id){
		BookSenseEntity bookSense = bookSenseService.getById(id);

        return R.ok().put("bookSense", bookSense);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("core:booksense:save")
    public R save(@RequestBody BookSenseEntity bookSense){
		bookSenseService.save(bookSense);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("core:booksense:update")
    public R update(@RequestBody BookSenseEntity bookSense){
		bookSenseService.updateById(bookSense);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("core:booksense:delete")
    public R delete(@RequestBody Integer[] ids){
		bookSenseService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
