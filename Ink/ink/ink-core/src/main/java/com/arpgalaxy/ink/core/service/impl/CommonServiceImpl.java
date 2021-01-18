package com.arpgalaxy.ink.core.service.impl;

import com.arpgalaxy.ink.core.dao.CommonDao;
import com.arpgalaxy.ink.core.service.CommonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author arpgalaxy
 * @date 2021/1/18
 * @email 13605560342@163.com
 * @description
 */
@Service("commonService")
public class CommonServiceImpl implements CommonService {
    @Resource
    private CommonDao commonDao;

    @Override
    public List<String> queryColumnNames(String tableName) {
        List<String> strings = commonDao.queryMenuColumnNames(tableName);
        return strings;
    }
}
