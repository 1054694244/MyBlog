package com.shenzc.controller;

import com.shenzc.resutl.ResultBody;
import com.shenzc.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/aliyun/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/sendMessage")
    public ResultBody sendMessage(@RequestParam(value = "paramMap")Map<String,Object> map){
        boolean b = messageService.sendMessage(map);
        return ResultBody.success(b);
    }

}
