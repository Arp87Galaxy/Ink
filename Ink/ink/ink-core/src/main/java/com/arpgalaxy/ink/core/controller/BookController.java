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

import com.arpgalaxy.ink.core.entity.BookEntity;
import com.arpgalaxy.ink.core.service.BookService;
import com.arpgalaxy.ink.common.utils.PageUtils;
import com.arpgalaxy.ink.common.utils.response.R;



/**
 * 图书表
 *
 * @author arpgalaxy
 * @email 13605560342@163.com
 * @date 2020-09-05 14:47:37
 */
@RestController
@RequestMapping("core/book")
public class BookController {
    @Autowired
    private BookService bookService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("core:book:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = bookService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("core:book:info")
    public R info(@PathVariable("id") Integer id){
		BookEntity book = bookService.getById(id);

        return R.ok().put("book", book);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("core:book:save")
    public R save(@RequestBody BookEntity book){
		bookService.save(book);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("core:book:update")
    public R update(@RequestBody BookEntity book){
		bookService.updateById(book);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("core:book:delete")
    public R delete(@RequestBody Integer[] ids){
		bookService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
