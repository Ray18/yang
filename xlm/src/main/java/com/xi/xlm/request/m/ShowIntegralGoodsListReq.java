package com.xi.xlm.request.m;

import lombok.Data;

/**
 * @className: showIntegralGoodsListReq
 * @Description:
 * @author:by yangtianfeng
 * @classDate: 2020/9/16 11:45
 * @Version: 1.0
 **/
@Data
public class ShowIntegralGoodsListReq {

    /**
     * 上下架状态0下架1上架
     */
    private Boolean upState;

    /**
     * 商品名称
     */
    private String goodsName;


}
