/*
 * Copyright (C) 1997-2020 康成投资（中国）有限公司
 *
 * http://www.rt-mart.com
 *
 * 版权归本公司所有，不得私自使用、拷贝、修改、删除，否则视为侵权
 */
package com.shenzc.controller;

import com.shenzc.resutl.ResultBody;
import com.shenzc.utils.FastJsonUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.mapping.ResultMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author Shenzc
 * @Date 2020/9/24 17:14
 */
@RestController
@RequestMapping("/echarts")
public class EchartsController {


    @RequestMapping("/geteEchartsData")
    public ResultBody geteEchartsData(String index){
        Map<String,Object> map = new HashMap<>();
        List<String> list  = new ArrayList<>();
        list.add("06.05");
        list.add("06.06");
        list.add("06.07");
        list.add("06.08");
        list.add("06.09");
        list.add("06.10");
        list.add("06.11");
        map.put("xData", list);
        /*{
            name: '总成交额',
                    type: 'line',
                stack: '总量',
                data: [120, 132, 101, 134, 90, 230, 210]
        },*/
        Map<String,Object> map1 = new HashMap<>();
        map1.put("name","总成交额");
        List<Long> longList = new ArrayList<>();
        longList.add(120L);
        longList.add(420L);
        longList.add(820L);
        longList.add(1220L);
        longList.add(1920L);
        longList.add(2120L);
        longList.add(2120L);
        map1.put("data",longList);
        map1.put("type","line");
        Map<String,Object> map2 = new HashMap<>();
        map2.put("name","较上一日成交额");
        List<Long> longList2 = new ArrayList<>();
        longList2.add(220L);
        longList2.add(400L);
        longList2.add(720L);
        longList2.add(1620L);
        longList2.add(1420L);
        longList2.add(6120L);
        longList2.add(2120L);
        map2.put("data",longList2);
        map2.put("type","line");
        List<Object> list1 = new ArrayList<>();
        list1.add(map2);
        list1.add(map1);
        map.put("data",list1);

        //设置y轴数据
        /*
        * [
                    {
                        type : 'value',
                        axisLabel: {
                            formatter: '{value} 元'
                        }
                    }
                ]
        * */
       /* List<Map<String,Object>> list2 = new ArrayList<>();
        Map<String,Object> map3 = new HashMap<>();
        map3.put("type","value");
        Map<String,Object> map4 = new HashMap<>();
        map3.put()
        list2.add()*/
        Map<String,Object> map3 = new HashMap<>();
        map3.put("formatter","{value}元");
        map.put("yData",map3);
        return ResultBody.success(map);
    }



}
