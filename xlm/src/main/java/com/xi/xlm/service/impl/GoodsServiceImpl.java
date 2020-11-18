package com.xi.xlm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.len.base.impl.BaseServiceImpl;
import com.xi.xlm.entity.Goods;
import com.xi.xlm.mapper.GoodsMapper;
import com.xi.xlm.request.ListGoodsRequest;
import com.xi.xlm.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @className: GoodsServiceImpl
 * @Description:
 * @author:by yangtianfeng
 * @classDate: 2020/8/14 9:14
 * @Version: 1.0
 **/
@Service
public class GoodsServiceImpl extends BaseServiceImpl<Goods, String> implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public IPage<Goods> list(ListGoodsRequest request) {
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        return goodsMapper.selectPage(request.getPage(), wrapper);
    }

    @Override
    public void addGood(Goods good) {
        goodsMapper.AddGood(good);
    }

    @Override
    public void delById(String id) {
        goodsMapper.delById(id);
    }

    @Override
    public void updateGood(Goods good) {
        goodsMapper.updateGood(good);
    }

    @Override
    public Goods getById(String id) {
        return goodsMapper.getById(id);
    }
}
