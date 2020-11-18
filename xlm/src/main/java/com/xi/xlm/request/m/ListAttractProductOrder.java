package com.xi.xlm.request.m;

import lombok.Data;

/**
 * @className: ListAttractProductOrder
 * @Description: 加盟产品订单查询条件
 * @author:by yangtianfeng
 * @classDate: 2020/8/25 16:09
 * @Version: 1.0
 **/
@Data
public class ListAttractProductOrder {

    /**
     * 0代付款1已付款2代发货3已发货
     */
    private Integer orderState;

    /**
     * 订单编号
     */
    private String orderNo;
    private String createDate;

}
