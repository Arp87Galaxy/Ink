package com.arpgalaxy.ink.core.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.arpgalaxy.ink.common.utils.PageUtils;
import com.arpgalaxy.ink.common.utils.Query;

import com.arpgalaxy.ink.core.dao.BookNoteDao;
import com.arpgalaxy.ink.core.entity.BookNoteEntity;
import com.arpgalaxy.ink.core.service.BookNoteService;


@Service("bookNoteService")
public class BookNoteServiceImpl extends ServiceImpl<BookNoteDao, BookNoteEntity> implements BookNoteService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BookNoteEntity> page = this.page(
                new Query<BookNoteEntity>().getPage(params),
                new QueryWrapper<BookNoteEntity>()
        );

        return new PageUtils(page);
    }

}