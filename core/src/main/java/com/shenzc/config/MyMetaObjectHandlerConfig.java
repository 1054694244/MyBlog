package com.shenzc.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class MyMetaObjectHandlerConfig implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("自动填充:insert");
        this.strictInsertFill(metaObject,"createAt", Date.class,new Date());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("自动填充:update");
        this.strictUpdateFill(metaObject,"updateAt", Date.class,new Date());
    }
}