package com.xi.xlm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xi.xlm.entity.AttracProductIntroduce;
import com.xi.xlm.entity.AttracProductIntroduceType;
import com.xi.xlm.mapper.ProductIntroduceTypeMapper;
import com.xi.xlm.request.ListAPITRequest;
import com.xi.xlm.service.ProductIntroduceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductIntroduceTypeServiceImpl extends ServiceImpl<ProductIntroduceTypeMapper,AttracProductIntroduceType> implements ProductIntroduceTypeService {
    @Autowired
    ProductIntroduceTypeMapper productIntroduceTypeMapper;

    @Override
    public IPage<AttracProductIntroduceType> list(ListAPITRequest request) {
        QueryWrapper<AttracProductIntroduceType> wrapper = new QueryWrapper<>();
        return productIntroduceTypeMapper.selectPage(request.getPage(), wrapper);
    }

    @Override
    public void addType(AttracProductIntroduceType attracProductIntroduceType) {
        productIntroduceTypeMapper.AddType(attracProductIntroduceType);
    }

    @Override
    public void delById(String id) {
        productIntroduceTypeMapper.delById(id);
    }

    @Override
    public void updateType(AttracProductIntroduceType attracProductIntroduceType) {
        productIntroduceTypeMapper.updateType(attracProductIntroduceType);
    }

    @Override
    public AttracProductIntroduceType getById(String id) {
        return productIntroduceTypeMapper.getById(id);
    }

    @Override
    public ArrayList<AttracProductIntroduceType> selectAll() {
        return productIntroduceTypeMapper.selectAll();
    }

    @Override
    public AttracProductIntroduce getByType(String type) {
        return productIntroduceTypeMapper.getByType(type);
    }
}
