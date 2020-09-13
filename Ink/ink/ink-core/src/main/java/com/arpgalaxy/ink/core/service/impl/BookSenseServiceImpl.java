package com.arpgalaxy.ink.core.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.arpgalaxy.ink.common.utils.PageUtils;
import com.arpgalaxy.ink.common.utils.Query;

import com.arpgalaxy.ink.core.dao.BookSenseDao;
import com.arpgalaxy.ink.core.entity.BookSenseEntity;
import com.arpgalaxy.ink.core.service.BookSenseService;


@Service("bookSenseService")
public class BookSenseServiceImpl extends ServiceImpl<BookSenseDao, BookSenseEntity> implements BookSenseService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BookSenseEntity> page = this.page(
                new Query<BookSenseEntity>().getPage(params),
                new QueryWrapper<BookSenseEntity>()
        );

        return new PageUtils(page);
    }

}