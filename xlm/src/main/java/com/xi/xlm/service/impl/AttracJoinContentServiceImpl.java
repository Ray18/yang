package com.xi.xlm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xi.xlm.entity.AttracCityCase;
import com.xi.xlm.entity.AttracJoinContent;
import com.xi.xlm.mapper.AttracJoinContentMapper;
import com.xi.xlm.request.ListACCRequest;
import com.xi.xlm.request.ListAJCRequest;
import com.xi.xlm.service.AttracJoinContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttracJoinContentServiceImpl  extends ServiceImpl<AttracJoinContentMapper,AttracJoinContent> implements AttracJoinContentService {
    @Autowired
    AttracJoinContentMapper attracJoinContentMapper;

    @Override
    public IPage<AttracJoinContent> list(ListAJCRequest request) {
        QueryWrapper<AttracJoinContent> wrapper = new QueryWrapper<>();
        return attracJoinContentMapper.selectPage(request.getPage(), wrapper);
    }


    @Override
    public void updateJoinContent(AttracJoinContent attracJoinContent) {
        attracJoinContentMapper.updateJoinContent(attracJoinContent);
    }

    @Override
    public AttracJoinContent getById(String id) {
        return attracJoinContentMapper.getById(id);
    }
}
