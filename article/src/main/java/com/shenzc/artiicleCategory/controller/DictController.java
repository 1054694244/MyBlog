package com.shenzc.artiicleCategory.controller;

import com.shenzc.artiicleCategory.service.DictService;
import com.shenzc.entity.backendUser.DictValue;
import com.shenzc.resutl.ResultBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/article/dict")
@Slf4j
public class DictController {

    @Autowired
    private DictService dictService;

    @GetMapping("getDictByName")
    public ResultBody getDictByName(@RequestParam(value = "dictName")String dictName){
        List<DictValue> dictValueList = null;
        try {
            dictValueList = dictService.getDictByName(dictName);
        }catch (Exception e){
            log.error("调用字典值失败"+e.getMessage(),e);
            return ResultBody.fail(500,e.getMessage());
        }
        return ResultBody.success(dictValueList);
    }

}
