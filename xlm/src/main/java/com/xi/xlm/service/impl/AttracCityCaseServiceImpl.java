package com.xi.xlm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xi.xlm.entity.AttracCityCase;
import com.xi.xlm.mapper.AttracCityCaseMapper;
import com.xi.xlm.request.ListACCRequest;
import com.xi.xlm.service.AttracCityCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttracCityCaseServiceImpl extends ServiceImpl<AttracCityCaseMapper,AttracCityCase> implements AttracCityCaseService {

    @Autowired
    AttracCityCaseMapper attracCityCaseMapper;

    @Override
    public IPage<AttracCityCase> list(ListACCRequest request) {
        QueryWrapper<AttracCityCase> wrapper = new QueryWrapper<>();
        return attracCityCaseMapper.selectPage(request.getPage(), wrapper);
    }

    @Override
    public void addCityCase(AttracCityCase attracCityCase) {
        attracCityCaseMapper.addCityCase(attracCityCase);
    }

    @Override
    public void delById(String id) {
        attracCityCaseMapper.delById(id);
    }

    @Override
    public void updateCityCase(AttracCityCase attracCityCase) {
        attracCityCaseMapper.updateCityCase(attracCityCase);
    }

    @Override
    public AttracCityCase getById(String id) {
        return attracCityCaseMapper.getById(id);
    }
}
