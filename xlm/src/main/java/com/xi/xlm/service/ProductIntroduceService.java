package com.xi.xlm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xi.xlm.entity.AttracProductIntroduce;
import com.xi.xlm.request.ListAPIRequest;


public interface ProductIntroduceService extends IService<AttracProductIntroduce> {
    IPage<AttracProductIntroduce> list(ListAPIRequest request);

    void addIntroduce(AttracProductIntroduce attracProductIntroduce);

    void delById(String id);

    void updateIntroduce(AttracProductIntroduce attracProductIntroduce);

    AttracProductIntroduce getById(String id);

}
