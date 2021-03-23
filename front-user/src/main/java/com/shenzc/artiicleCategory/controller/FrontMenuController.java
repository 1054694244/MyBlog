package com.shenzc.artiicleCategory.controller;

import com.shenzc.artiicleCategory.entity.FrontMenuVo;
import com.shenzc.artiicleCategory.service.FrontMenuService;
import com.shenzc.resutl.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/frontManage/menu")
public class FrontMenuController {

    @Autowired
    private FrontMenuService frontMenuService;

    @RequestMapping("/getAllMenu")
    public ResultBody getAllMenu(){
        List<FrontMenuVo> allMenu = frontMenuService.getAllMenu();
        return ResultBody.success(allMenu);
    }

}
