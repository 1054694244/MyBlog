package com.shenzc.artiicleCategory.service;

import com.shenzc.artiicleCategory.mapper.FileMapper;
import com.shenzc.artiicleCategory.vo.FileVo;
import com.shenzc.service.IdService;
import com.shenzc.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {

    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private IdService idService;
    /**
     * 添加文件
     * @param fileVo
     */
    public void addFile(FileVo fileVo){
        fileVo.setFileId(idService.generateIdByDate());
        if (fileVo.getCategoryId().contains("/")){
            String category = fileVo.getCategoryId().substring(fileVo.getCategoryId().lastIndexOf("/")+1);
            fileVo.setCategoryId(category);
        }
        fileVo.setCreateBy(JwtUtil.username());
        fileMapper.insert(fileVo);
    }

}
