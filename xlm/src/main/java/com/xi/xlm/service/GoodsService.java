package com.xi.xlm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.len.base.BaseService;
import com.xi.xlm.entity.Goods;
import com.xi.xlm.request.ListGoodsRequest;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-08-14
 */
public interface GoodsService extends BaseService<Goods, String> {
    IPage<Goods> list(ListGoodsRequest request);

    void addGood(Goods good);

    void delById(String id);

    void updateGood(Goods good);

    Goods getById(String id);
}
