package com.xi.xlm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xi.xlm.entity.AttracCityCase;
import com.xi.xlm.request.ListACCRequest;


public interface AttracCityCaseService extends IService<AttracCityCase> {
    IPage<AttracCityCase> list(ListACCRequest request);

    void addCityCase(AttracCityCase attracCityCase);

    void delById(String id);

    void updateCityCase(AttracCityCase attracCityCase);

    AttracCityCase getById(String id);
}
