package com.shenzc.controller;

import com.shenzc.resutl.ResultBody;
import com.shenzc.service.OssService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/aliyun/oss")
public class OssController {

    @Autowired
    private OssService ossService;

    @PostMapping("/upload")
    public ResultBody upload(@RequestParam(value = "file")MultipartFile file,
                             @RequestParam(value = "uploadUrl")String uploadUrl){
            Map<String,String> map = null;
        try{
            if (!"".equals(uploadUrl)){
                ossService.delete(uploadUrl);
            }
            map = ossService.upload(file);
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ResultBody.success(map);
    }

}
