package com.shenzc.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class IdService {

    @Resource
    private RedisTemplate redisTemplate;

    public String generateIdByRedis(String prefix){
        long no1 = redisTemplate.opsForValue().increment(prefix);
        String no = String.format("%05d", no1);
        return prefix + no;
    }

    public String generateIdByDate(){
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy");
        Date date = new Date();
        String year = simpleDateFormat1.format(date);
        long  time = date.getTime();
        String noStr = String.format("%07d", time);
        return year + noStr;
    }
}