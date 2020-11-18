package com.xi.xlm.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @className: FieldMetaObjectHandler
 * @Description:公 s共s字s段自s动填s充
 * @author:by yangtianfeng
 * @classDate: 2020/8/18 15:26
 * @Version: 1.0
 **/
@Component
@Slf4j
public class FieldMetaObjectHandler implements MetaObjectHandler {
    private final static String CREATE_TIME = "createDate";
    private final static String CREATER = "createBy";
    private final static String UPDATE_TIME = "updateDate";
    private final static String UPDATER = "updaterBy";
    private final static String DELETED = "delFlag";

    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime time = LocalDateTime.now();

        this.setFieldValByName( CREATE_TIME,time, metaObject);
        this.setFieldValByName( DELETED,false, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //setFieldValByName(UPDATER, SecurityUser.getUserId(),metaObject);
        setFieldValByName(UPDATE_TIME, LocalDateTime.now(),metaObject);

    }
}
