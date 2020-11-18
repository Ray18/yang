package com.xi.xlm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xi.xlm.entity.AttracProductIntroduce;
import com.xi.xlm.mapper.ProductIntroduceMapper;
import com.xi.xlm.request.ListAPIRequest;
import com.xi.xlm.service.ProductIntroduceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductIntroduceServiceImpl extends ServiceImpl<ProductIntroduceMapper,AttracProductIntroduce> implements ProductIntroduceService {

    @Autowired
    ProductIntroduceMapper productIntroduceMapper;

    @Override
    public IPage<AttracProductIntroduce> list(ListAPIRequest request) {
        QueryWrapper<AttracProductIntroduce> wrapper = new QueryWrapper<>();
        return productIntroduceMapper.selectPage(request.getPage(), wrapper);
    }


    @Override
    public void addIntroduce(AttracProductIntroduce attracCityCase) {
        productIntroduceMapper.addIntroduce(attracCityCase);
    }

    @Override
    public void delById(String id) {
        productIntroduceMapper.delById(id);
    }

    @Override
    public void updateIntroduce(AttracProductIntroduce attracCityCase) {
        productIntroduceMapper.updateIntroduce(attracCityCase);
    }

    @Override
    public AttracProductIntroduce getById(String id) {
        return productIntroduceMapper.getById(id);
    }


}
