package com.shenzc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class IdService {

    @Resource
    private RedisTemplate redisTemplate;

    public String generateId(String prefix){
        long no1 = redisTemplate.opsForValue().increment(prefix);
        String no = String.format("%05d", no1);
        return prefix + no;
    }

    private String generateId(long no){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        Date date = new Date();
        String year = simpleDateFormat.format(date);
        String noStr = String.format("%07d", no);
        return year + noStr;
    }
}