package com.xi.xlm.request.m;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @className: AddAttractProduct
 * @Description: 添加招商板块-加盟产品
 * @author:by yangtianfeng
 * @classDate: 2020/8/24 14:53
 * @Version: 1.0
 **/
@Data
public class AddAttractProduct {
    private String id;
    /**
     * 产品名称
     */
    private String productName;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 封面
     */
    private String coverImg;

    /**
     * 图文详情
     */
    private String productDetails;

    /**
     * 0下架1上架
     */
    private Boolean upState;


}
