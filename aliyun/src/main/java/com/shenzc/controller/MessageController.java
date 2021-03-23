package com.shenzc.controller;

import com.shenzc.resutl.ResultBody;
import com.shenzc.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/aliyun/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/sendMessage")
    public ResultBody sendMessage(@RequestParam(value = "phone")String phone,
                                  @RequestParam(value = "code")String code){
        Map<String,Object> map = new HashMap<>();
        map.put("phone",phone);
        map.put("code",code);
        boolean b = messageService.sendMessage(map);
        return ResultBody.success(b);
    }

}
