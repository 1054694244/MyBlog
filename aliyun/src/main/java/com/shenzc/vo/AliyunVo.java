package com.shenzc.vo;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AliyunVo implements InitializingBean {

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

    @Value("${aliyun.endpoint}")
    private String endpoint;

    @Value("${aliyun.bucketName}")
    private String bucketName;

    @Value("${aliyun.fileHost}")
    private String fileHost;

    public static String ACCESSKEYID;
    public static String ACCESSSECRET;
    public static String SIGNNAME;
    public static String TEMPLATECODE;
    public static String REGIONID;
    public static String SYSDOMAIN;
    public static String SYSVERSION;
    public static String SYSACTION;
    public static String ENDPOINT;
    public static String BUCKETNAME;
    public static String FILEHOST;

    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESSKEYID      = accessKeyId ;
        ACCESSSECRET     = accessSecret;
        SIGNNAME         = SignName    ;
        TEMPLATECODE     = TemplateCode;
        REGIONID         = RegionId    ;
        SYSDOMAIN        = sysDomain   ;
        SYSVERSION       = sysVersion  ;
        SYSACTION        = sysAction   ;
        ENDPOINT         = endpoint    ;
        BUCKETNAME       = bucketName  ;
        FILEHOST         = fileHost    ;
    }
}
