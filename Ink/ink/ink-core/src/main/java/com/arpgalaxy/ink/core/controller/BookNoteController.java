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

import com.arpgalaxy.ink.core.entity.BookNoteEntity;
import com.arpgalaxy.ink.core.service.BookNoteService;
import com.arpgalaxy.ink.common.utils.PageUtils;
import com.arpgalaxy.ink.common.utils.R;



/**
 * 笔记
 *
 * @author arpgalaxy
 * @email 13605560342@163.com
 * @date 2020-09-05 14:47:37
 */
@RestController
@RequestMapping("core/booknote")
public class BookNoteController {
    @Autowired
    private BookNoteService bookNoteService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("core:booknote:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = bookNoteService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("core:booknote:info")
    public R info(@PathVariable("id") Integer id){
		BookNoteEntity bookNote = bookNoteService.getById(id);

        return R.ok().put("bookNote", bookNote);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("core:booknote:save")
    public R save(@RequestBody BookNoteEntity bookNote){
		bookNoteService.save(bookNote);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("core:booknote:update")
    public R update(@RequestBody BookNoteEntity bookNote){
		bookNoteService.updateById(bookNote);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("core:booknote:delete")
    public R delete(@RequestBody Integer[] ids){
		bookNoteService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
