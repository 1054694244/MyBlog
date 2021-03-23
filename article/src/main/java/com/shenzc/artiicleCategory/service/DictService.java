/*
 * Copyright (C) 1997-2020 康成投资（中国）有限公司
 *
 * http://www.rt-mart.com
 *
 * 版权归本公司所有，不得私自使用、拷贝、修改、删除，否则视为侵权
 */
package com.shenzc.artiicleCategory.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shenzc.artiicleCategory.mapper.DictMapper;
import com.shenzc.artiicleCategory.mapper.DictValueMapper;
import com.shenzc.entity.backendUser.Dict;
import com.shenzc.entity.backendUser.DictValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author Shenzc
 * @Date 2020/9/29 14:56
 */
@Service
public class DictService {

    @Autowired
    private DictMapper dictMapper;
    @Autowired
    private DictValueMapper dictValueMapper;


    public List<DictValue> getDictByName(String dictName) {
        Dict dict = dictMapper.selectOne(new QueryWrapper<Dict>().eq("dict_name", dictName));
        if (dict == null){
            throw new RuntimeException("当前字典值没有："+dictName);
        }
        List<DictValue> dictValueList = dictValueMapper.selectList(new QueryWrapper<DictValue>().eq("dict_id", dict.getDictId()));
        return dictValueList;
    }
}
