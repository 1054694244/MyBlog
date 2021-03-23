package com.shenzc.artiicleCategory.controller;

import com.shenzc.artiicleCategory.service.FileService;
import com.shenzc.artiicleCategory.vo.FileVo;
import com.shenzc.resutl.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/selfArticle/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/submitFile")
    public ResultBody addFile(@RequestBody FileVo fileVo){
        fileService.addFile(fileVo);
        return ResultBody.success("");
    }

}
