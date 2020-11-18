package com.xi.xlm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xi.xlm.entity.AttracProductIntroduce;
import com.xi.xlm.entity.AttracProductIntroduceType;
import com.xi.xlm.request.ListAPITRequest;

import java.util.ArrayList;


public interface ProductIntroduceTypeService  extends IService<AttracProductIntroduceType> {
    IPage<AttracProductIntroduceType> list(ListAPITRequest request);

    void addType(AttracProductIntroduceType attracProductIntroduceType);

    void delById(String id);

    void updateType(AttracProductIntroduceType attracProductIntroduceType);

    AttracProductIntroduceType getById(String id);

    ArrayList<AttracProductIntroduceType> selectAll();

    AttracProductIntroduce getByType(String type);
}
