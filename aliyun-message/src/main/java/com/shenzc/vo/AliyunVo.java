package com.shenzc.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AliyunVo {

    @Value("${aliyun.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.accessSecret}")
    private String accessSecret;

    @Value("${aliyun.SignName}")
    private String SignName;

    @Value("${aliyun.TemplateCode}")
    private String TemplateCode;

    @Value("${aliyun.RegionId}")
    private String RegionId;

    @Value("${aliyun.sysDomain}")
    private String sysDomain;

    @Value("${aliyun.sysVersion}")
    private String sysVersion;

    @Value("${aliyun.sysAction}")
    private String sysAction;

}
